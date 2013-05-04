package server.battlehandler;

import server.Room;
import server.Services;
import server.SocketCommunicator;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;

/**
 * Created with IntelliJ IDEA.
 * User: s3342128
 * Date: 16/04/13
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class BattleThread implements Runnable {

    private SocketCommunicator communicator;
    private BattleServer server;

    public BattleThread(SocketCommunicator com, BattleServer s) {
        communicator = com;
        server = s;
    }

    @Override
    public void run() {
        System.out.println("BattleThread running");

        try {
            int service;

            while (!communicator.isClosed()) {
                service = communicator.readInt();

                System.out.println("BattleThread service: " + service);
                switch (service) {
                    case Services.BATTLE_INITIALIZATION: {
                        initBattle();
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

    private void initBattle() {
        server.initBattle();
    }

    public void stopThread() {
        communicator.close();
    }

    public SocketCommunicator getCommunicator() {
        return communicator;
    }
}
