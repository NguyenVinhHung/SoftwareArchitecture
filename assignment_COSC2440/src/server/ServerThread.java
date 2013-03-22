package server;

import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/21/13
 * Time: 9:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServerThread implements Runnable {

    private Socket socket;

    public ServerThread(Socket s) {
        socket = s;
    }

    @Override
    public void run() {

    }
}
