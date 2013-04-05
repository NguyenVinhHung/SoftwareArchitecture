package chathandler;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: s3309665
 * Date: 5/04/13
 * Time: 10:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class ChatServerThread extends Thread {
    private ChatCommunicator chatCommunicator;

    public ChatServerThread(ChatCommunicator chatCommunicator) {
        this.chatCommunicator = chatCommunicator;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int globalOrTeam = chatCommunicator.getObjectInputStream().readInt();
                String mess = chatCommunicator.getObjectInputStream().readUTF();

                if (globalOrTeam == ChatServices.GLOBAL_CHAT) {
                    handleGlobalChat(mess);
                } else if (globalOrTeam == ChatServices.TEAM_1_CHAT) {
                    handleTeamChat1(mess);
                } else {
                    handleTeamChat2(mess);
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot get message from client :(");
        }
    }

    public void handleGlobalChat(String mess) {
        System.out.println("Global chat " + mess);
        handleTeamChat1(mess);
        handleTeamChat2(mess);

    }

    public void handleTeamChat1(String mess) {
        try {
            System.out.println("Team chat 1 " + mess);
            for (int i = 0; i < ChatServer.serverThreadVectorTeam1.size(); i++) {
                ChatServer.serverThreadVectorTeam1.get(i).chatCommunicator.getObjectOutputStream().writeUTF(mess);
                ChatServer.serverThreadVectorTeam1.get(i).chatCommunicator.getObjectOutputStream().flush();
            }
        } catch (IOException e) {
            System.out.println("Cannot handle team chat 1");
        }
    }

    public void handleTeamChat2(String mess) {
        try {
            System.out.println("Team chat 2 " + mess);
            for (int i = 0; i < ChatServer.serverThreadVectorTeam2.size(); i++) {
                ChatServer.serverThreadVectorTeam2.get(i).chatCommunicator.getObjectOutputStream().writeUTF(mess);
                ChatServer.serverThreadVectorTeam2.get(i).chatCommunicator.getObjectOutputStream().flush();
            }
        } catch (IOException e) {
            System.out.println("Cannot handle team chat 2");
        }
    }

    public ChatCommunicator getChatCommunicator() {
        return chatCommunicator;
    }
}
