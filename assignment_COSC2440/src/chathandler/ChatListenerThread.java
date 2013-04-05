package chathandler;

import model.Player;
import server.SocketCommunicator;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: s3309665
 * Date: 5/04/13
 * Time: 12:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatListenerThread extends Thread {

//    Socket socket;
//    ObjectOutputStream objectOutputStream;
//    ObjectInputStream objectInputStream;
    private JTextArea chatBox;
    private SocketCommunicator communicator;

    public ChatListenerThread(Socket socket, ObjectOutputStream objectOutputStream,
                              ObjectInputStream objectInputStream, Player p) {
//        this.socket = socket;
//        this.objectOutputStream = objectOutputStream;
//        this.objectInputStream = objectInputStream;
        communicator = new SocketCommunicator(socket, objectOutputStream, objectInputStream, p);
    }

    public ChatListenerThread(SocketCommunicator sc, JTextArea chatBox) {
        communicator = sc;
        this.chatBox = chatBox;
    }

    @Override
    public void run() {
//        try {
            while (true) {
                String mess = (String)communicator.read();
//                System.out.println(mess);
                chatBox.append(mess);
                chatBox.setCaretPosition(chatBox.getDocument().getLength());
            }
//        } catch (IOException e) {
//
//        }
    }


}
