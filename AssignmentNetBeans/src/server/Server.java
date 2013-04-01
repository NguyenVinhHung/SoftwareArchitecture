package server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import utility.DatabaseUtil;
import utility.SpringUtil;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

    public static final String IP = "localhost";
    public static final int PORT_NUM = 16453;

    private ServerSocket serverSocket;
    private JTextArea log;

    public static void main(String args[]) {
        DatabaseUtil.load();
//        ApplicationContext serverCtx = new ClassPathXmlApplicationContext("server_beans.xml");
        Server server = (Server) SpringUtil.getServerBean("server");

        server.runServer();
    }

    private void init() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("Server closed");
                DatabaseUtil.save();
            }
        });

        add(log);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
//        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void runServer() {
        try {
            //serverSocket = new ServerSocket(PORT_NUM);

            while(true) {
//                System.out.println("Server is running");
                Socket socket = serverSocket.accept();

                new Thread(new ServerThread(socket)).start();
            }
        } catch(Exception ex) {
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
