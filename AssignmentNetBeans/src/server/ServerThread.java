package server;

import model.Player;
import model.room.RoomPublicInfo;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import utility.DatabaseUtil;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/21/13
 * Time: 9:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServerThread implements Runnable {

    private SocketCommunicator communicator;
    private Socket socket;
    private Player player;

    public ServerThread(Socket s) {
        socket = s;
    }

    @Override
    public void run() {
//        while(true) {
//            try {
//                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
//                int service = input.readInt();
//
//                switch(service) {
//                    case Services.REGISTER:
//                        register(input);
//                        break;
//                    case Services.LOGIN:
//                        login();
//                        break;
//                    case Services.LOGOUT:
//                        input.close();
//                        return;
//                }
//
//                input.close();
//            } catch(Exception ex) {
//                return;
//            }
//        }


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
//                        Object obj = input.readObject();
                        retreiveRoomList(output);
                        break;
                    }
                    case Services.CREATE_ROOM: {
                        createRoom(input, output);
                        break;
                    }
                    case Services.GET_IN_ROOM: {
                        getInRoom();
                        break;
                    }
                    case Services.IN_ROOM_GET_SELECTED_POK: {
                        System.out.println("Get selected pokemon infos in room");
                        getSelectedPokesInRoom();
                        break;
                    }
                    case Services.IN_ROOM_GET_POK_LIST: {
                        System.out.println("Get pokemon list");
                        getPokemonList();
                        break;
                    }
                    case Services.NOTIFY: {
                        System.out.println("Notify players");
                        notifyPlayers();
                        break;
                    }
                    case Services.LOGOUT: {
                        logout();
                        return;
                    }
                }
            }
        } catch (EOFException eof) {
            logout();
            System.out.println("Close Socket Communicator due to EOFException");
        } catch (SocketException eof) {
            logout();
            System.out.println("Close Socket Communicator due to SocketException");
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

    }

    private synchronized void register(ObjectInputStream input, ObjectOutputStream output) {
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

    private synchronized boolean login(ObjectInputStream input, ObjectOutputStream output) {
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

    private synchronized void retreiveRoomList(ObjectOutputStream output) {
        System.out.println("Retreive Room List begin");

        OnlinePlayerList opl = (OnlinePlayerList)ServerSpring.getBean("onlinePlayerList");
        ArrayList<RoomPublicInfo> roomInfos = new ArrayList<RoomPublicInfo>();
        Vector<Room> rooms = opl.getRoomsAsVector();

        System.out.println("Retreive Room List start the loop");

        for(int i=0; i<rooms.size(); i++) {
            int type = rooms.get(i).getNumPlayersPerTeam();
            RoomPublicInfo rpi = new RoomPublicInfo("Room " + (i+1), type, rooms.get(i).getHostName());
            roomInfos.add(rpi);
            System.out.println("In ServerThread: " + rpi);
        }

        System.out.println("Retreive room list end getting array");

        communicator.write(roomInfos);
        communicator.flushOutput();

        System.out.println("Retreive room list send back to user");
    }

    private synchronized void createRoom(ObjectInputStream input, ObjectOutputStream output) throws Exception {
        OnlinePlayerList opl = (OnlinePlayerList)ServerSpring.getBean("onlinePlayerList");
        int type = (Integer)input.readObject();

        System.out.println("Room type: " + type);

        Room r = new Room(communicator, type);
        String hostname = opl.addRoom(r);
        RoomPublicInfo rpi = new RoomPublicInfo("", type, r.getHostName());

        System.out.println("Creating room response to user");
        communicator.write(rpi);
        communicator.flushOutput();
        System.out.println("Creating room end");
    }

    private synchronized void logout() {
        OnlinePlayerList opl = (OnlinePlayerList)ServerSpring.getBean("onlinePlayerList");

//        if(opl.getWaitingPlayers().contains(communicator)) {
            opl.logoutPlayer(communicator, communicator.getUsername());
//        }

        communicator.close();
    }

    private synchronized void getInRoom() {
        System.out.println("getInRoom Begin");
        OnlinePlayerList opl = (OnlinePlayerList)ServerSpring.getBean("onlinePlayerList");
        String host = (String)communicator.read();

        System.out.println("getInRoom get Room");
        Room r = opl.getRooms().get(host);
        int result = r.addPlayer(communicator);

        if(result == Room.ADD_PLAYER_FAILED) {
            System.out.println("FAILED");
            communicator.write(new Integer(Services.GET_IN_ROOM_FAILED));
            communicator.flushOutput();
        } else {
            System.out.println("SUCCESS");
            opl.getWaitingPlayers().remove(communicator);
            communicator.write(new Integer(Services.GET_IN_ROOM_SUCCESS));
            communicator.write(new RoomPublicInfo("Room #", r.getNumPlayersPerTeam(), r.getHostName()));
            communicator.write(new Integer(result));
            communicator.flushOutput();


//            System.out.println("getInRoom notify room");
//            int notifyResponse = (result==Room.TEAM_1) ? Services.GET_IN_ROOM_T1 : Services.GET_IN_ROOM_T2;
//            opl.notifyRoom(host, notifyResponse, result, communicator);
        }

        System.out.println("getInRoom end");
    }

    private synchronized void getSelectedPokesInRoom() {
        System.out.println("getSelectedPokesInRoom Begin");
        OnlinePlayerList opl = (OnlinePlayerList)ServerSpring.getBean("onlinePlayerList");
        String host = (String)communicator.read();

        System.out.println("getSelectedPokesInRoom get Room");
        Room r = opl.getRooms().get(host);

        System.out.println("getSelectedPokesInRoom Start sending result");
        communicator.write(r.getSelectedPokeInfoTeam(Room.TEAM_1));
        communicator.write(r.getSelectedPokeInfoTeam(Room.TEAM_2));
        communicator.flushOutput();

        System.out.println("getSelectedPokesInRoom End");
    }

    private void getPokemonList() {
        communicator.write(communicator.getPlayer().getPokemons());
        communicator.flushOutput();
    }

    private void notifyPlayers() {
        OnlinePlayerList opl = (OnlinePlayerList)ServerSpring.getBean("onlinePlayerList");
        String host = (String)communicator.read();
        int result = (Integer)communicator.read();

        System.out.println("notify players");
        int notifyResponse = (result==Room.TEAM_1) ? Services.GET_IN_ROOM_T1 : Services.GET_IN_ROOM_T2;
        opl.notifyRoom(host, notifyResponse, result, communicator);
    }
}
