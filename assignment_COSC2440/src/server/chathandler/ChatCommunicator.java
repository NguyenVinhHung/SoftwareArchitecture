package server.chathandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: s3309665
 * Date: 5/04/13
 * Time: 10:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class ChatCommunicator {

    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public ChatCommunicator(Socket socket, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
        this.socket = socket;
        this.objectInputStream = objectInputStream;
        this.objectOutputStream = objectOutputStream;
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public void close() {
        try {
            objectOutputStream.close();
            objectInputStream.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Close chat communicator in " + this.getClass().getName());
        }
    }
}
