package chathandler;

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
public class ChatServer {

    public static Vector<String> mess = new Vector<String>();
    public static Vector<ChatServerThread> serverThreadVectorTeam1 = new Vector<ChatServerThread>();
    public static Vector<ChatServerThread> serverThreadVectorTeam2 = new Vector<ChatServerThread>();
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private int numOfPlayers;
    private int port;
    private int countPlayers;
    private ServerSocket serverSocket;
    private Socket socket;

    public ChatServer(int port) {
        this.port = port;
    }

    public void run() {
        try {

            serverSocket = new ServerSocket(port);
            while (true) {
                socket = serverSocket.accept();

                objectInputStream = new ObjectInputStream(socket.getInputStream());
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                ChatCommunicator chatCommunicator = new ChatCommunicator(socket, objectInputStream, objectOutputStream);

                ChatServerThread serverThread = new ChatServerThread(chatCommunicator);

                // Get user team
                int team1Orteam2 = chatCommunicator.getObjectInputStream().readInt();

                if (team1Orteam2 == ChatServices.JOIN_TEAM_1) {
                    serverThreadVectorTeam1.add(serverThread);
                } else {
                    serverThreadVectorTeam2.add(serverThread);
                }
                // Start Thread to listen for message from client;
                serverThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void stop() {
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
}
