package server.battlehandler;

import server.Server;
import server.SocketCommunicator;
import server.chathandler.ChatServices;

import java.io.IOException;
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
public class BattleServer extends Thread {

    private final Vector<BattleThread> team1Threads = new Vector<BattleThread>();
    private final Vector<BattleThread> team2Threads = new Vector<BattleThread>();

    private ServerSocket serverSocket;
    private int port;
    private boolean running;

    public BattleServer(int port) {
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
            while(running) {
                Socket socket = serverSocket.accept();
                SocketCommunicator communicator = new SocketCommunicator(socket);
                BattleThread battleThread = new BattleThread(communicator);

                // Get user team
                int team1Orteam2 = communicator.getFrom().readInt();

                if (team1Orteam2 == ChatServices.JOIN_TEAM_1) {
                    team1Threads.add(battleThread);
                } else {
                    team2Threads.add(battleThread);
                }

                // Start Thread
                new Thread(battleThread).start();
            }
        } catch (Exception ex) {
        }
    }

    public void stopThread() {
        try {
            for (int i = 0; i < team1Threads.size(); i++) {
                team1Threads.get(i).stopThread();
            }

            for (int i = 0; i < team2Threads.size(); i++) {
                team2Threads.get(i).stopThread();
            }
            serverSocket.close();
            Server.USING_PORT.remove(port);

        } catch (IOException e) {
            System.out.println("Close the connection ");
        }
    }
}
