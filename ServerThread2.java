package server;

import model.Player;
import model.room.RoomPublicInfo;
import utility.DatabaseUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/21/13
 * Time: 9:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServerThread2 implements Runnable {

    private SocketCommunicator communicator;
    private Socket socket;
    private Player player;
    private int playerIdx;

    public ServerThread2(Socket s, int playerIdx) {
        socket = s;
        this.playerIdx = playerIdx;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            int service;

            System.out.println("ServerThread started");

            while (true) {
                System.out.println("ServerThread is running");
                service = input.readInt();
                System.out.println("Service number: " + service);

                switch (service) {
                    case Services.REGISTER: {
                        register(input, output);
                        return;
                    }
                    case Services.LOGIN: {
                        if(login(input, output)) {
                            System.out.println("Login successful");
                            break;
                        } else {
                            System.out.println("Login failed");
                            return;
                        }
                    }
                    case Services.ROOM_LIST: {
                        System.out.println("retreiveRoomList");
                        Object obj = input.readObject();
                        retreiveRoomList(output);
                        break;
                    }
                    case Services.CREATE_ROOM: {
                        createRoom(input, output);
                        break;
                    }
                    case Services.LOGOUT: {
                        input.close();
                        return;
                    }
                }

                if(playerIdx == 0) {
                    createRoom(input, output);
                }

                OnlinePlayerList opl = (OnlinePlayerList)ServerSpring.getBean("onlinePlayerList");
                Room r = opl.getRoomsAsVector().get(0);
                r.addPlayer(communicator);

//                output.writeInt(50);
                output.writeBoolean(playerIdx == 0);
                output.writeObject(r.getPlayerTeam(true));
                output.writeObject(r.getPlayerTeam(false));
            }
        } catch (Exception ex) {
            return;
        }

    }

    private void register(ObjectInputStream input, ObjectOutputStream output) {
        System.out.println("Adding new account");
        try {
            String username = input.readUTF();
            String pw = input.readUTF();

//            input.close();

            if(DatabaseUtil.addPlayer(username, pw) == null) {
                System.out.println("Adding account failed due to duplicate name");
                output.writeInt(Services.REGISTER_FAILED_DUPLICATE_NAME);
            } else {
                System.out.println("Adding new account success");
                output.writeInt(Services.REGISTER_SUCCESS);
            }

            output.flush();

            input.close();
            output.close();
            socket.close();
        } catch(Exception ex) {
            System.out.println("Adding account has error");
        }
    }

    private boolean login(ObjectInputStream input, ObjectOutputStream output) {
        System.out.println("Login");
        try {
            String username = input.readUTF();
            String pw = input.readUTF();
            Player p = DatabaseUtil.getPlayers().get(username);

            if(p == null) {
                output.writeInt(Services.LOGIN_WRONG_USER);
                output.flush();
                input.close();
                output.close();
                socket.close();
                return false;
            } else if(!p.getPw().equals(pw)) {
                output.writeInt(Services.LOGIN_WRONG_PW);
                output.flush();
                input.close();
                output.close();
                socket.close();
                return false;
            } else {
                output.writeInt(Services.LOGIN_SUCCESS);
                output.writeObject(p);
                output.flush();

                OnlinePlayerList opl = (OnlinePlayerList)ServerSpring.getBean("onlinePlayerList");
                communicator = new SocketCommunicator(socket, output, input, p);
                opl.loginPlayer(communicator);
                return true;
            }
        } catch(Exception ex) {
            System.out.println("Adding account has error");
            return false;
        }
    }

    private void retreiveRoomList(ObjectOutputStream output) throws IOException {
        OnlinePlayerList opl = (OnlinePlayerList)ServerSpring.getBean("onlinePlayerList");
        ArrayList<RoomPublicInfo> roomInfos = new ArrayList<RoomPublicInfo>();
        Vector<Room> rooms = opl.getRoomsAsVector();

        System.out.println("Retreive room list intialize");

        for(int i=0; i<rooms.size(); i++) {
            System.out.println("Retreive room list " + i);
            int type = rooms.get(i).getNumPlayersPerTeam();
            roomInfos.add(new RoomPublicInfo("Room " + (i+1), type + " Vs " + type, rooms.get(i).getHostName()));
        }

        System.out.println("Retreive room list end getting array");

        output.writeObject(roomInfos);
        System.out.println("Retreive room list send back to user");
    }

    private void createRoom(ObjectInputStream input, ObjectOutputStream output) throws Exception {
        System.out.println("Creating room");

        OnlinePlayerList opl = (OnlinePlayerList)ServerSpring.getBean("onlinePlayerList");

        System.out.println("Creating room getting OnlinePlayerList");

//        int type = (Integer)input.readObject();
        int type = 3;

        System.out.println("Creating room read players per team");

        Room r = new Room(communicator, type);
        int roomIdx = opl.addRoom(r);

        System.out.println("Creating room response to user");
        output.writeInt(roomIdx);
        System.out.println("Creating room end");
    }
}
