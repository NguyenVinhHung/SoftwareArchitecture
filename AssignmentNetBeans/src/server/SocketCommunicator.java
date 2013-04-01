package server;

import model.Player;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: s3342128
 * Date: 29/03/13
 * Time: 9:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class SocketCommunicator {
    
    private Socket socket;
    private ObjectInputStream from;
    private ObjectOutputStream to;
    private Player player;
    
//    public SocketCommunicator(Socket s, Player p) {
    public SocketCommunicator(Socket s) {
        socket = s;
//        player = p;
        
        try {
            from = new ObjectInputStream(socket.getInputStream());
        } catch(Exception ex) { 
            System.out.println("Error getting INPUT STREAM");
        }
        
        try {
            to = new ObjectOutputStream(socket.getOutputStream());
        } catch(Exception ex) {        
            System.out.println("Error getting OUTPUT STREAM");
        }
    }

    public void write(Object o) {
        try {
            to.writeObject(o);
        } catch(Exception ex) {
        }
    }

    public Object read() {
        try {
            return from.readObject();
        } catch(Exception ex) {
            return null;
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectInputStream getFrom() {
        return from;
    }

    public ObjectOutputStream getTo() {
        return to;
    }
}
