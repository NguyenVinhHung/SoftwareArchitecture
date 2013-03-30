package server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import utility.DatabaseUtil;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/21/13
 * Time: 7:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Server {

    public static final int PORT_NUM = 16453;

    private ServerSocket serverSocket;

    public static void main(String args[]) {
        DatabaseUtil.load();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("server_beans.xml");
        Server server = (Server)ctx.getBean("server");

        server.runServer();
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
}
