package server.battlehandler;

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

    public BattleThread(SocketCommunicator com) {
        communicator = com;
    }

    @Override
    public void run() {
        try {
            int service;

            while (!communicator.isClosed()) {
                service = communicator.readInt();

                switch (service) {
                    case Services.REGISTER: {
//                        register(input, output);
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

    public void stopThread() {
        communicator.close();
    }
}
