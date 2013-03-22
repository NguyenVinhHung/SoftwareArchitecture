package server;

import main.TimeCounter;

import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/21/13
 * Time: 7:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Server extends JFrame {

    public static final int PORT_NUM = 16453;

    public static void main(String args[]) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT_NUM);

            while(true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerThread(socket)).start();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
