package server.waitingroomhandler;

import model.pokemon.SelectedPokeInfo;
import server.*;

/**
 * Created with IntelliJ IDEA.
 * User: s3342128
 * Date: 16/04/13
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class WaitingRoomThread implements Runnable {

    private WaitingRoomServer server;
    private SocketCommunicator communicator;
    private String username;

    public WaitingRoomThread(WaitingRoomServer server, SocketCommunicator com, String username) {
        this.server = server;
        communicator = com;
        this.username = username;
    }

    @Override
    public void run() {
        try {
            int service = 0;

            System.out.println("WaitingRoomThread is running");

            while (!communicator.isClosed() && service != Services.INVALID) {
                service = communicator.readInt();

                System.out.println("WaitingRoomThread request: " + service);
                switch (service) {
                    case Services.IN_ROOM_NOTIFY_SELECTED_POK: {
                        notifySelectedPokesInRoom();
                        break;
                    }
                    case Services.BATTLE_START: {
                        startBattle();
                        break;
                    }
                    case Services.IN_ROOM_STOP_WAITING: {
                        stopThread();
                        return;
                    }
                }
            }

//        } catch (EOFException eof) {
////            logout();
//            System.out.println("Close Socket Communicator due to EOFException");
//        } catch (SocketException eof) {
////            logout();
//            System.out.println("Close Socket Communicator due to SocketException");
        } catch (Exception ex) {
            System.out.println("OTHER EXCEPTION");
            ex.printStackTrace();
            return;
        }
    }

    private synchronized void notifySelectedPokesInRoom() {
//        OnlinePlayerList opl = (OnlinePlayerList) ServerSpring.getBean("onlinePlayerList");
//        String host = (String)communicator.read();
//        Room r = opl.getRooms().get(host);
        Room r = getRoom();

//        r.notifySelectedPoke();

        System.out.println("Start getting SelectedPokeInfoTeam");
        server.notifyPlayers(r.getSelectedPokeInfoTeam(Room.TEAM_1), r.getSelectedPokeInfoTeam(Room.TEAM_2));
        System.out.println("Finish getting SelectedPokeInfoTeam");
    }

    private void startBattle() {
        Room r = getRoom();

        r.startBattle();
    }

    public void stopThread() {
        communicator.close();
    }

    private Room getRoom() {
        OnlinePlayerList opl = (OnlinePlayerList) ServerSpring.getBean("onlinePlayerList");
        String host = (String) communicator.read();
        return opl.getRooms().get(host);
    }

    public boolean isStopped() {
        return communicator.isClosed();
    }

    public SocketCommunicator getCommunicator() {
        return communicator;
    }

    public String getUsername() {
        return username;
    }
}
