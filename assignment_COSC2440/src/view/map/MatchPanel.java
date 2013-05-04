/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.map;

import model.pokemon.PokeInBattleInfo;
import model.room.RoomPublicInfo;
import server.Server;
import server.Services;
import server.battlehandler.BattleListenerThread;
import server.chathandler.ChatListenerThread;
import server.chathandler.ChatServices;
import main.Main;
import server.SocketCommunicator;
import view.panel.SocketClosable;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.*;

/**
 *
 * @author HungHandsome
 */
public class MatchPanel extends JPanel implements KeyListener, SocketClosable {

    private static final int CHATBOX_Y = 200;
    private static final int CHATBOX_W = 200;
    private static final int CHATBOX_H = 400;
    private static final int CHATTYPER_Y = CHATBOX_Y + CHATBOX_H + 10;
    private static final int CHATTYPER_H = 150;

    private GameMap map;
    private GameThread gameThread;
//    private JTabbedPane chatTabs;
    private JTextArea chatBox;
//    private JTextArea teamChat;
    private JTextArea chatTyper;
    //    private Socket chatSocket;
    private SocketCommunicator chatCommunicator;
    private SocketCommunicator battleCommunicator;
    private BattleListenerThread battleListenerThread;
    private String hostName;
    private RoomPublicInfo roomInfo;
    private boolean isTeam1;
    
    public MatchPanel(GameMap m, SocketCommunicator chatCommunicator,
                      ChatListenerThread chatListenerThread, String hostName) {
        init(m, chatCommunicator, chatListenerThread, hostName, null);
    }

    public MatchPanel(int[][] mapArr, SocketCommunicator chatCommunicator, ChatListenerThread chatListenerThread
                        , boolean isTeam1, String hostName, RoomPublicInfo roomInfo) {
        this.isTeam1 = isTeam1;
        init(new GameMap(this, mapArr), chatCommunicator, chatListenerThread, hostName, roomInfo);
    }

    private void init(GameMap m, SocketCommunicator chatCommunicator,
                      ChatListenerThread chatListenerThread, String hostName, RoomPublicInfo roomInfo) {
        map = m;
        this.chatCommunicator = chatCommunicator;
        this.hostName = hostName;
        this.roomInfo = roomInfo;

        setLayout(null);
        initChatFeature();

        chatListenerThread.setChatBox(chatBox);

//        SocketCommunicator sc = Main.getCommunicator();
//        sc.sendRequestHeader(Services.BATTLE_INITIALIZATION);
//        sc.write(hostName);
//        sc.flushOutput();
//
//        String firstPlayer = (String)sc.read();
//
//        map.setMyTurn(firstPlayer.equals(sc.getUsername()));
//        map.setMyPoke((PokeInBattleInfo)sc.read());
//        map.setPokeModels1((PokeInBattleInfo[])sc.read());
//        map.setPokeModels2((PokeInBattleInfo[])sc.read());

//        gameThread = new GameThread();
//
//        if(!map.isMyTurn()) {
//            gameThread.start();
//        }

        initBattleSocket();
    }

    private void initChatFeature() {
        chatBox = new JTextArea();
        chatTyper = new JTextArea();

        chatBox.setBounds(GameMap.WIDTH + 10, CHATBOX_Y, CHATBOX_W, CHATBOX_H);
        chatTyper.setBounds(GameMap.WIDTH + 10, CHATTYPER_Y, CHATBOX_W, CHATTYPER_H);

        chatTyper.addKeyListener(this);

        chatBox.setText("");
        chatBox.setEditable(false);
        chatBox.setLineWrap(true);

        add(chatBox);
        add(chatTyper);
    }

    private void initBattleSocket() {
        try {
            Socket socket = new Socket(Server.IP, roomInfo.getRoomServerPort());

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            battleCommunicator = new SocketCommunicator(socket, objectOutputStream, objectInputStream, null);

            objectOutputStream.writeInt((isTeam1) ? ChatServices.JOIN_TEAM_1 : ChatServices.JOIN_TEAM_2);
            objectOutputStream.flush();
//
            if(roomInfo.getHostname().equals(Main.getCommunicator().getUsername())) {
                battleCommunicator.sendRequestHeader(Services.BATTLE_INITIALIZATION);
                battleCommunicator.flushOutput();
            }
//
            battleListenerThread = new BattleListenerThread(battleCommunicator, this);
            battleListenerThread.start();

        } catch (IOException e) {
            System.out.println("Cannot create chat socket");
        }
    }

    public void initPokeOnMap(PokeInBattleInfo[] t1, PokeInBattleInfo[] t2) {
        map.setPokeModels1(t1);
        map.setPokeModels2(t2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.fillRect(0, 0, getWidth(), getHeight());

        map.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            System.out.println("Send msg");
            String text = chatTyper.getText();

            if(text.startsWith("/ ")) {
                chatCommunicator.write(new Integer(ChatServices.GLOBAL_CHAT));
                text = "[ALL] " + Main.getCommunicator().getUsername() + ": " + text.substring(2);
            } else if(isTeam1) {
                chatCommunicator.write(new Integer(ChatServices.TEAM_1_CHAT));
                text = Main.getCommunicator().getUsername() + ": " + text;
            } else {
                chatCommunicator.write(new Integer(ChatServices.TEAM_2_CHAT));
                text = Main.getCommunicator().getUsername() + ": " + text;
            }

            chatCommunicator.write(text);
            chatCommunicator.flushOutput();
            chatTyper.setText("");
        }
    }

    @Override
    public void closeSocket() {
        chatCommunicator.close();
        battleCommunicator.close();
    }

    private class GameThread extends Thread {
        @Override
        public void run() {
            SocketCommunicator sc = Main.getCommunicator();

            while(!map.isMyTurn()) {
                int action = (Integer)sc.read();


            }
        }
    }
}
