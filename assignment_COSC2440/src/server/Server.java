package server;

import utility.DatabaseUtil;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/21/13
 * Time: 7:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Server extends JFrame {

    public static final String IP = "localhost";
    public static final int PORT_NUM = 16453;
    public static final Set<Integer> PORT_CHAT_SERVER = new LinkedHashSet<Integer>();

    private ServerSocket serverSocket;
    private JTextArea log;

    public static void main(String args[]) {
        DatabaseUtil.load();
        Server server = (Server) ServerSpring.getBean("server");
        server.runServer();
    }

    public static void getPortForChatServer() {
        int port = 0;
        Random random = new Random();
        boolean isValid = false;

        while (!isValid) {
            while (port < 1024) {
                port = random.nextInt(65536);
            }
            if (PORT_CHAT_SERVER.add(port)) {
                isValid = true;
            } else {
                isValid = false;
            }
        }

    }

    private void init() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Server closing");
                DatabaseUtil.save();
            }
        });

        add(log);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void runServer() {
        try {
            while (true) {
                System.out.println("Server is waiting for Socket");
                Socket socket = serverSocket.accept();
                System.out.println("Server receive Socket");
                new Thread(new ServerThread(socket)).start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void setLog(JTextArea log) {
        this.log = log;
    }
}
