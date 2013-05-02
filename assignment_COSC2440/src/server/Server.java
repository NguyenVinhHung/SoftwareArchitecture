package server;

//import utility.DatabaseUtil;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
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
    public static final int MAIN_PORT = 16453;
    public static final Set<Integer> USING_PORT = new LinkedHashSet<Integer>();

    private ServerSocket serverSocket;
    private JTextArea log;

    public static void main(String args[]) {
//        DatabaseUtil.load();
        Server server = (Server) ServerSpring.getBean("server");
        server.runServer();
    }

    public static int makeNewPort() {
        int port = 0;
        Random random = new Random(Calendar.getInstance().getTimeInMillis());

        while (true) {
            port = random.nextInt(64511) + 1025;
            if (port!=MAIN_PORT && USING_PORT.add(port)) {
                return port;
            }
        }
    }

    private void init() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Server closing");
//                DatabaseUtil.save();
            }
        });

        add(log);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void close() {
        System.out.println("Server close()");
//        DatabaseUtil.save();
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
