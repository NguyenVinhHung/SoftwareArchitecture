package chathandler;

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
    Socket socket;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public ChatListenerThread(Socket socket, ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
        this.socket = socket;
        this.objectOutputStream = objectOutputStream;
        this.objectInputStream = objectInputStream;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String mess = objectInputStream.readUTF();
                System.out.println(mess);
            }
        } catch (IOException e) {

        }
    }
}
