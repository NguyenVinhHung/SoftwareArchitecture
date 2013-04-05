package chathandler;

import server.SocketCommunicator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: s3309665
 * Date: 5/04/13
 * Time: 9:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class ChatServer extends Thread {

    private final Vector<ChatServerThread> serverThreadVectorTeam1 = new Vector<ChatServerThread>();
    private final Vector<ChatServerThread> serverThreadVectorTeam2 = new Vector<ChatServerThread>();

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private ServerSocket serverSocket;
    private Socket socket;
    private int numOfPlayers;
    private int port;
    private int countPlayers;

    public ChatServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                socket = serverSocket.accept();
                SocketCommunicator chatCommunicator = new SocketCommunicator(socket);
                ChatServerThread serverThread = new ChatServerThread(this, chatCommunicator);

                // Get user team
                int team1Orteam2 = chatCommunicator.getFrom().readInt();

                if (team1Orteam2 == ChatServices.JOIN_TEAM_1) {
                    serverThreadVectorTeam1.add(serverThread);
                } else {
                    serverThreadVectorTeam2.add(serverThread);
                }
                // Start Thread to listen for message from client;
                serverThread.start();
            }
        } catch (IOException e) {
            System.out.println("Close server chat");
        }
    }

    public void closePlaySocket(String username) {
        try {
            for (int i = 0; i < serverThreadVectorTeam1.size(); i++) {
                ChatServerThread csv = serverThreadVectorTeam1.get(i);
                if(csv.getUsername().equals(username)) {
                    csv.getChatCommunicator().close();
                    return;
                }
            }

            for (int i = 0; i < serverThreadVectorTeam2.size(); i++) {
                ChatServerThread csv = serverThreadVectorTeam2.get(i);
                if(csv.getUsername().equals(username)) {
                    csv.getChatCommunicator().close();
                    return;
                }
            }
        } catch(Exception ex) {
//            serverThreadVectorTeam1.clear();
        }
    }

    public void stopThread() {
        try {
            for (int i = 0; i < serverThreadVectorTeam1.size(); i++) {
                serverThreadVectorTeam1.get(i).getChatCommunicator().close();
            }

            for (int i = 0; i < serverThreadVectorTeam2.size(); i++) {
                serverThreadVectorTeam2.get(i).getChatCommunicator().close();
            }
            serverSocket.close();

        } catch (IOException e) {
            System.out.println("Close the connection ");
        }
    }

    public Vector<ChatServerThread> getServerThreadVectorTeam1() {
        return serverThreadVectorTeam1;
    }

    public Vector<ChatServerThread> getServerThreadVectorTeam2() {
        return serverThreadVectorTeam2;
    }
}
