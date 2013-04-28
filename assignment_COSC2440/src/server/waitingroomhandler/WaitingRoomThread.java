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

    private SocketCommunicator communicator;
    private String username;

    public WaitingRoomThread(SocketCommunicator com, String username) {
        communicator = com;
        this.username = username;
    }

    @Override
    public void run() {
        try {
            int service = 0;

            System.out.println("WaitingRoomThread is running");

            while (!communicator.isClosed() && service!=Services.INVALID) {
                service = communicator.readInt();

                System.out.println("WaitingRoomThread requesr: " + service);
                switch (service) {
                    case Services.IN_ROOM_NOTIFY_SELECTED_POK: {
                        notifySelectedPokesInRoom();
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
        OnlinePlayerList opl = (OnlinePlayerList) ServerSpring.getBean("onlinePlayerList");
        String host = (String)communicator.read();
        Room r = opl.getRooms().get(host);
//        r.notifySelectedPoke();

        System.out.println("Start getting SelectedPokeInfoTeam");
        communicator.write(r.getSelectedPokeInfoTeam(Room.TEAM_1));
        communicator.write(r.getSelectedPokeInfoTeam(Room.TEAM_2));
        communicator.flushOutput();
        System.out.println("Finish getting SelectedPokeInfoTeam");
    }

    public void stopThread() {
        communicator.close();
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
