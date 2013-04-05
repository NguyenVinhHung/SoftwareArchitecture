package server;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/31/13
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class OnlinePlayerList {

    private Vector<SocketCommunicator> waitingPlayers;
    private Map<String, Room> rooms;

    public OnlinePlayerList() {
        waitingPlayers = new Vector<SocketCommunicator>();
        rooms = new LinkedHashMap<String, Room>();
    }

    public void loginPlayer(SocketCommunicator sc) {
        waitingPlayers.add(sc);
        System.out.println("Player " + sc.getUsername() + " login successfully");
    }

    public void logoutPlayer(SocketCommunicator sc, String roomHostname) {
        System.out.println("Logout player");
        waitingPlayers.remove(sc);
        if(rooms.containsKey(roomHostname)) {
            Room r = rooms.get(roomHostname);
            if(r.removePlayer(sc) == Room.REMOVE_PLAYER_AND_ROOM) {
                System.out.println("Remove Room");
                rooms.remove(r.getHostName()).close();
            }
        }
    }

    public int moveToRoom(SocketCommunicator sc, String roomHostname) {
        waitingPlayers.remove(sc);
        int result = rooms.get(roomHostname).addPlayer(sc);

        if(result != Room.ADD_PLAYER_FAILED) {
            int notifyResponse = (result==Room.TEAM_1) ? Services.GET_IN_ROOM_T1 : Services.GET_IN_ROOM_T2;
            notifyRoom(roomHostname, notifyResponse, result, sc);
        }
        return result;
    }

    public void getOutRoom(SocketCommunicator sc, String roomHostname) {
        waitingPlayers.add(sc);
        Room r = rooms.get(roomHostname);

        if(r.removePlayer(sc) == Room.REMOVE_PLAYER_AND_ROOM) {
            rooms.remove(r.getHostName()).close();
        }
    }

    public void changeRoomHost(SocketCommunicator sc, String roomHostname) {
        Room r = rooms.remove(roomHostname);

        if(r.changeRoomHost(sc) == Room.REMOVE_ONLY_PLAYER) {
            rooms.put(r.getHostName(), r);
        }
    }

    public String addRoom(Room r) {
        rooms.put(r.getHostName(), r);
        waitingPlayers.remove(r.getTeam1().get(r.getHostName()));
        return r.getHostName();
    }

    public void notifyAllPlayers(int msg, SocketCommunicator except) {
        notifyWaitingPlayers(msg);
        for(Room r : rooms.values()) {
            r.notifyAllPlayers(msg, except);
        }
    }

    public void notifyWaitingPlayers(int msg) {
        for(SocketCommunicator sc : waitingPlayers) {
            sc.sendRequestHeader(msg);
            sc.flushOutput();
        }
    }

    public void notifyRoom(String hostname, int msg, int team, SocketCommunicator except) {
        System.out.println("Notify Room " + hostname);
        if(team == Room.TEAM_1) {
            rooms.get(hostname).notifyTeam1(msg, except);
        } else if(team == Room.TEAM_2) {
            rooms.get(hostname).notifyTeam2(msg, except);
        } else {
            rooms.get(hostname).notifyAllPlayers(msg, except);
        }
    }

    public Vector<SocketCommunicator> getWaitingPlayers() {
        return waitingPlayers;
    }

    public Map<String, Room> getRooms() {
        return rooms;
    }

    public Vector<Room> getRoomsAsVector() {
        return new Vector<Room>(rooms.values());
    }

}
