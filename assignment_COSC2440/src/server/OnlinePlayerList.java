package server;

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
    private Vector<Room> rooms;

    public OnlinePlayerList() {
        waitingPlayers = new Vector<SocketCommunicator>();
        rooms = new Vector<Room>();
    }

    public void loginPlayer(SocketCommunicator sc) {
        waitingPlayers.add(sc);
    }

    public void logoutPlayer(SocketCommunicator sc, int roomIdx) {
        waitingPlayers.remove(sc);
        if(roomIdx >= 0) {
            rooms.get(roomIdx).removePlayer(sc);
        }
    }

    public void moveToRoom(SocketCommunicator sc, int roomIdx) {
        waitingPlayers.remove(sc);
        rooms.get(roomIdx).addPlayer(sc);
    }

    public void getOutRoom(SocketCommunicator sc, int roomIdx) {
        waitingPlayers.add(sc);
        Room r = rooms.get(roomIdx);
        r.removePlayer(sc);

        if(r.isEmpty()) {
            rooms.remove(r);
        }
    }

    public int addRoom(Room r) {
        rooms.add(r);
        waitingPlayers.remove(r.getTeam1().get(r.getHostName()));
        return rooms.size() - 1;
    }

    public Vector<SocketCommunicator> getWaitingPlayers() {
        return waitingPlayers;
    }

    public Vector<Room> getRooms() {
        return rooms;
    }
}
