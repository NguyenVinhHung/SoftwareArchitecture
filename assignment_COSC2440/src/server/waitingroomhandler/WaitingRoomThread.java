package server.waitingroomhandler;

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

    public WaitingRoomThread(SocketCommunicator com) {
        communicator = com;
    }

    @Override
    public void run() {
        try {
            int service;

            while (!communicator.isClosed()) {
                service = communicator.readInt();

                switch (service) {
                    case Services.IN_ROOM_NOTIFY_SELECTED_POK: {
                        notifySelectedPokesInRoom();
                        break;
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
        r.notifySelectedPoke();
    }

    public void stopThread() {
        communicator.close();
    }
}
