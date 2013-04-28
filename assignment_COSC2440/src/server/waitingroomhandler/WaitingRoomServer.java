package server.waitingroomhandler;

import model.Player;
import model.pokemon.SelectedPokeInfo;
import server.Room;
import server.Server;
import server.Services;
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
//    private Room room;
    private int port;
    private boolean running;

    public WaitingRoomServer(int port) {
        this.port = port;
//        this.room = room;

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

//                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
//                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                SocketCommunicator communicator = new SocketCommunicator(socket);
                Player p = (Player)communicator.read();
                WaitingRoomThread thread = new WaitingRoomThread(this, communicator, (String)communicator.read());

                communicator.setPlayer(p);

                threads.add(thread);
                new Thread(thread).start();
            }
        } catch (Exception ex) {
        }
    }

    public void notifyPlayers(SelectedPokeInfo[] team1, SelectedPokeInfo[] team2) {
        System.out.println("notifyPlayers - Notify players");
        for(WaitingRoomThread thread : threads) {
            thread.getCommunicator().sendRequestHeader(Services.IN_ROOM_NOTIFY_SELECTED_POK);
            thread.getCommunicator().write(team1);
            thread.getCommunicator().write(team2);
            thread.getCommunicator().flushOutput();
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

    public void closePlayerSocket(String username) {
        WaitingRoomThread t = null;

        for(WaitingRoomThread thr : threads) {
            if(thr.getUsername().equals(username)) {
                t = thr;
                break;
            }
        }

        if(t != null) {
//            t.getCommunicator().sendRequestHeader(Services.IN_ROOM_STOP_WAITING);
//            t.getCommunicator().flushOutput();
            t.stopThread();
            threads.remove(t);
        }
    }
}
