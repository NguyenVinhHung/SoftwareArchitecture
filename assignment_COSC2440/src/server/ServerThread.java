package server;

import model.Player;
import utility.DatabaseUtil;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private Player player;

    public ServerThread(Socket s) {
        socket = s;
    }

    @Override
    public void run() {
//        while(true) {
//            try {
//                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
//                int service = input.readInt();
//
//                switch(service) {
//                    case Services.REGISTER:
//                        register(input);
//                        break;
//                    case Services.LOGIN:
//                        login();
//                        break;
//                    case Services.LOGOUT:
//                        input.close();
//                        return;
//                }
//
//                input.close();
//            } catch(Exception ex) {
//                return;
//            }
//        }


        try {
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            int service;

            while (true) {
                service = input.readInt();
                switch (service) {
                    case Services.REGISTER: {
                        register(input);
                        return;
                    }
                    case Services.LOGIN: {
                        login();
                        break;
                    }
                    case Services.LOGOUT: {
                        input.close();
                        return;
                    }
                }
            }
        } catch (Exception ex) {
            return;
        }

    }

    private void register(ObjectInputStream input) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            String username = input.readUTF();
            String pw = input.readUTF();

            if(DatabaseUtil.addPlayer(username, pw) == null) {
                output.writeInt(Services.REGISTER_FAILED_DUPLICATE_NAME);
            } else {
                output.writeInt(Services.REGISTER_SUCCESS);
            }

            output.flush();
            output.close();
        } catch(Exception ex) {
        }
    }

    private void login() {

    }
}
