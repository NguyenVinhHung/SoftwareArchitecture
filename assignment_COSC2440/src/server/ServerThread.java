package server;

import model.Player;
import model.room.RoomPublicInfo;
import utility.DatabaseUtil;

import java.io.EOFException;
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
        try {
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            int service;

            while (true) {
                service = input.readInt();

                switch (service) {
                    case Services.REGISTER: {
                        register(input, output);
                        return;
                    }
                    case Services.LOGIN: {
                        if(login(input, output)) {
                            break;
                        } else {
                            return;
                        }
                    }
                    case Services.ROOM_LIST: {
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
                        getSelectedPokesInRoom();
                        break;
                    }
                    case Services.IN_ROOM_GET_POK_LIST: {
                        getPokemonList();
                        break;
                    }
                    case Services.BATTLE_START: {
                        startBattle();
                        break;
                    }
                    case Services.BATTLE_CHECK_STATE: {
                        checkBattleState();
                        break;
                    }
                    case Services.BATTLE_MOVE: {
                        checkBattleState();
                        break;
                    }
                    case Services.BATTLE_ATK: {
                        checkBattleState();
                        break;
                    }
                    case Services.BATTLE_END_TURN: {
                        checkBattleState();
                        break;
                    }
                    case Services.NOTIFY: {
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
        try {
            String username = input.readUTF();
            String pw = input.readUTF();

            if(DatabaseUtil.addPlayer(username, pw) == null) {
                output.writeInt(Services.REGISTER_FAILED_DUPLICATE_NAME);
            } else {
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
        OnlinePlayerList opl = (OnlinePlayerList)ServerSpring.getBean("onlinePlayerList");
        ArrayList<RoomPublicInfo> roomInfos = new ArrayList<RoomPublicInfo>();
        Vector<Room> rooms = opl.getRoomsAsVector();

        for(int i=0; i<rooms.size(); i++) {
            int type = rooms.get(i).getNumPlayersPerTeam();
            RoomPublicInfo rpi = new RoomPublicInfo("Room " + (i+1), type, rooms.get(i).getHostName(), 0);
            roomInfos.add(rpi);
        }

        communicator.write(roomInfos);
        communicator.flushOutput();
    }

    private synchronized void createRoom(ObjectInputStream input, ObjectOutputStream output) throws Exception {
        OnlinePlayerList opl = (OnlinePlayerList)ServerSpring.getBean("onlinePlayerList");
        int type = (Integer)input.readObject();
        Room r = new Room(communicator, type);
        String hostname = opl.addRoom(r);
        RoomPublicInfo rpi = new RoomPublicInfo("", type, r.getHostName(), r.getChatServerPort());

        communicator.write(rpi);
        communicator.flushOutput();
    }

    private synchronized void logout() {
        OnlinePlayerList opl = (OnlinePlayerList)ServerSpring.getBean("onlinePlayerList");
        opl.logoutPlayer(communicator, communicator.getUsername());
        communicator.close();
    }

    private synchronized void getInRoom() {
        OnlinePlayerList opl = (OnlinePlayerList)ServerSpring.getBean("onlinePlayerList");
        String host = (String)communicator.read();

        Room r = opl.getRooms().get(host);
        int result = r.addPlayer(communicator);

        if(result == Room.ADD_PLAYER_FAILED) {
            communicator.write(new Integer(Services.GET_IN_ROOM_FAILED));
            communicator.flushOutput();
        } else {
            opl.getWaitingPlayers().remove(communicator);
            communicator.write(new Integer(Services.GET_IN_ROOM_SUCCESS));
            communicator.write(new RoomPublicInfo("Room #", r.getNumPlayersPerTeam(), r.getHostName(), r.getChatServerPort()));
            communicator.write(new Integer(result));
            communicator.flushOutput();
        }
    }

    private synchronized void getSelectedPokesInRoom() {
        OnlinePlayerList opl = (OnlinePlayerList)ServerSpring.getBean("onlinePlayerList");
        String host = (String)communicator.read();
        Room r = opl.getRooms().get(host);

        communicator.write(r.getSelectedPokeInfoTeam(Room.TEAM_1));
        communicator.write(r.getSelectedPokeInfoTeam(Room.TEAM_2));
        communicator.flushOutput();
    }

    private void getPokemonList() {
        communicator.write(communicator.getPlayer().getPokemons());
        communicator.flushOutput();
    }

    private void startBattle() {
        OnlinePlayerList opl = (OnlinePlayerList)ServerSpring.getBean("onlinePlayerList");
        Room r = opl.getRooms().get(communicator.getUsername());

        System.out.println("startBattle get room");

        r.setState(Room.PLAYING_STATE);
        communicator.write(new Integer(Services.BATTLE_START));
        communicator.flushOutput();
    }

    private void checkBattleState() {
        OnlinePlayerList opl = (OnlinePlayerList)ServerSpring.getBean("onlinePlayerList");
        String host = (String)communicator.read();
        Room r = opl.getRooms().get(host);

        System.out.println("checkBattleState get room");

        if(r.getState() == Room.PLAYING_STATE) {
            System.out.println("checkBattleState start the battle");
            communicator.write(new Integer(Services.BATTLE_START));
        } else {
            System.out.println("checkBattleState still waiting");
            communicator.write(new Integer(0));
        }
        communicator.flushOutput();
    }

    private void moveLocation() {

    }

    private void attack() {

    }

    private void endTurn() {

    }

    private void notifyPlayers() {
        OnlinePlayerList opl = (OnlinePlayerList)ServerSpring.getBean("onlinePlayerList");
        String host = (String)communicator.read();
        int result = (Integer)communicator.read();

        int notifyResponse = (result==Room.TEAM_1) ? Services.GET_IN_ROOM_T1 : Services.GET_IN_ROOM_T2;
        opl.notifyRoom(host, notifyResponse, result, communicator);
    }
}
