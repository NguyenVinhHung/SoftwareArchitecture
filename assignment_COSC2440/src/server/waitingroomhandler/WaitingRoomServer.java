package server.waitingroomhandler;

import server.Server;
import server.SocketCommunicator;
import server.chathandler.ChatServices;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: s3342128
 * Date: 16/04/13
 * Time: 1:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class WaitingRoomServer extends Thread {

//    private final Vector<WaitingRoomThread> team1Threads = new Vector<WaitingRoomThread>();
//    private final Vector<WaitingRoomThread> team2Threads = new Vector<WaitingRoomThread>();
    private final Vector<WaitingRoomThread> threads = new Vector<WaitingRoomThread>();

    private ServerSocket serverSocket;
    private int port;
    private boolean running;

    public WaitingRoomServer(int port) {
        this.port = port;

        try {
            serverSocket = new ServerSocket(port);
        } catch(IOException ex) {
        }
    }

    @Override
    public void run() {
        running = true;

        try {
            while(!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();

                System.out.println("New Room socket created");

//                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
//                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                SocketCommunicator communicator = new SocketCommunicator(socket);

                System.out.println("Room socket communicator created");

                WaitingRoomThread thread = new WaitingRoomThread(communicator);

                System.out.println("Start new room server thread");
                threads.add(thread);
                new Thread(thread).start();
            }
        } catch (Exception ex) {
        }
    }

    public void stopThread() {
        try {
            for (int i = 0; i < threads.size(); i++) {
                threads.get(i).stopThread();
            }
            serverSocket.close();
            Server.USING_PORT.remove(port);

        } catch (IOException e) {
            System.out.println("Close the connection ");
        }
    }
}
