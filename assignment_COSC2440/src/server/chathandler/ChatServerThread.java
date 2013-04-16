package server.chathandler;

import server.SocketCommunicator;

/**
 * Created with IntelliJ IDEA.
 * User: s3309665
 * Date: 5/04/13
 * Time: 10:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class ChatServerThread extends Thread {

    private SocketCommunicator chatCommunicator;
    private ChatServer chatServer;

    public ChatServerThread(ChatServer cs, SocketCommunicator chatCommunicator) {
        chatServer = cs;
        this.chatCommunicator = chatCommunicator;
    }

    @Override
    public void run() {
        while (chatCommunicator==null || !chatCommunicator.getSocket().isClosed()) {
            try {
                int globalOrTeam = (Integer)chatCommunicator.read();
                String mess = (String) chatCommunicator.read();

                if (globalOrTeam == ChatServices.GLOBAL_CHAT) {
                    handleGlobalChat(mess);
                } else if (globalOrTeam == ChatServices.TEAM_1_CHAT) {
                    handleTeamChat1(mess);
                } else {
                    handleTeamChat2(mess);
                }
            } catch (Exception ex) {
                break;
            }
        }
    }

    public void handleGlobalChat(String mess) {
        System.out.println("Global chat " + mess);
        handleTeamChat1(mess);
        handleTeamChat2(mess);

    }

    public void handleTeamChat1(String mess) {
//        try {
            System.out.println("Team chat 1 " + mess);
            for (int i = 0; i < chatServer.getServerThreadVectorTeam1().size(); i++) {
                chatServer.getServerThreadVectorTeam1().get(i).chatCommunicator.write(mess);
                chatServer.getServerThreadVectorTeam1().get(i).chatCommunicator.flushOutput();
            }
//        } catch (IOException e) {
//            System.out.println("Cannot handle team chat 1");
//        }
    }

    public void handleTeamChat2(String mess) {
//        try {
            System.out.println("Team chat 2 " + mess);
            for (int i = 0; i < chatServer.getServerThreadVectorTeam2().size(); i++) {
                chatServer.getServerThreadVectorTeam2().get(i).chatCommunicator.write(mess);
                chatServer.getServerThreadVectorTeam2().get(i).chatCommunicator.flushOutput();
            }
//        } catch (IOException e) {
//            System.out.println("Cannot handle team chat 2");
//        }
    }

    public SocketCommunicator getChatCommunicator() {
        return chatCommunicator;
    }

    public String getUsername() {
        return chatCommunicator.getUsername();
    }
}
