package server;

import model.Player;

import java.io.*;
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

    public SocketCommunicator(Socket s, ObjectOutputStream output, ObjectInputStream input, Player p) {
        socket = s;
        from = input;
        to = output;
        player = p;
    }

    public void sendRequestHeader(int request) {
        System.out.println("Sending request header");
        try {
            to.writeInt(request);
        } catch(Exception ex) {
        }
    }

    public void write(Object o) {
        try {
            to.writeObject(o);
        } catch(InvalidClassException ex) {
            System.out.println("EXCEPTION IN WRITING OBJECT InvalidClassException");
        } catch(NotSerializableException ex) {
            System.out.println("EXCEPTION IN WRITING OBJECT NotSerializableException");
        } catch(IOException ex) {
            System.out.println("EXCEPTION IN WRITING OBJECT IOException");
        }
//        } catch(Exception ex) {
//            System.out.println("EXCEPTION IN WRITING OBJECT");
//        }
    }

    public void flushOutput() {
        try {
            to.flush();
        } catch(Exception ex) {
            System.out.println("EXCEPTION IN flushing OBJECT");
        }
    }

    public Object read() {
        try {
            return from.readObject();
        } catch(Exception ex) {
            return null;
        }
    }

    public void close() {
        if(socket.isClosed()) {
            return;
        }

        try {
            from.close();
            to.close();
            socket.close();
        } catch(Exception ex) {
        }
    }

    public String getUsername() {
        return player.getUsername();
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

    public Player getPlayer() {
        return player;
    }
}
