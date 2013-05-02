package view.panel;

import model.Player;
import model.pokemon.Pokemon;
import server.chathandler.ChatListenerThread;
import server.chathandler.ChatServices;
import main.Main;
import model.pokemon.PokemonFactory;
import model.pokemon.SelectedPokeInfo;
import model.room.RoomPublicInfo;
import server.Server;
import server.Services;
import server.SocketCommunicator;
import server.waitingroomhandler.WaitingRoomListenerThread;
import utility.FileUtility;
import view.customview.ImageButton;
import view.map.MapUtil;
import view.map.MatchPanel;
import view.smallview.PokeForSelectView;
import view.smallview.SelectedPokeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 4/1/13
 * Time: 7:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class RoomView extends AfterLoginTemplate implements KeyListener, SocketClosable {

    private static final int LEFT_COL_X = 0;
    private static final int RIGHT_COL_X = 812;
    private static final int CHATBOX_X = 208;
    private static final int CHATBOX_Y = 490;
    private static final int CHATBOX_H = 198;
    private static final int CHATTYPER_Y = 690;
    private static final int CHATTYPER_H = 70;
    private static final int LAYER_W2 = 604;

    private ArrayList<SelectedPokeView> g1;
    private ArrayList<SelectedPokeView> g2;
    private LinkedHashMap<String, SelectedPokeInfo> t1;
    private LinkedHashMap<String, SelectedPokeInfo> t2;
    private ArrayList<PokeForSelectView> pokeListView;
    private JTabbedPane chatTabs;
    private JTextArea globalChat;
    private JTextArea teamChat;
    private JTextArea chatTyper;
    //    private Socket chatSocket;

    private RoomPublicInfo roomInfo;

    private SocketCommunicator chatCommunicator;
    private ChatListenerThread chatListenerThread;

    private SocketCommunicator waitingRoomCommunicator;
    private WaitingRoomListenerThread waitingRoomListenerThread;

//    private boolean waiting = true;
    private boolean isTeam1;
//    private int roomIdx;

    public RoomView(RoomPublicInfo info, int teamNo) {
        roomInfo = info;
        final SocketCommunicator sc = Main.getCommunicator();

//        try {
////            roomIdx = sc.getFrom().readInt();
////            host = sc.getFrom().readBoolean();
////            t1 = (LinkedHashMap<String, SelectedPokeInfo>)sc.read();
////            t2 = (LinkedHashMap<String, SelectedPokeInfo>)sc.read();
////            roomInfo = (RoomPublicInfo)sc.read();
//        } catch (Exception ex) {
//        }
        sc.sendRequestHeader(Services.IN_ROOM_GET_POK_LIST);
        sc.flushOutput();

        initCreateBtn(sc);
        initPokemonList(sc);
        initChatFeature();
        initSelectedPokeView();

        isTeam1 = teamNo==-1;

//        try {
//            Socket chatSocket = new Socket(Server.IP, roomInfo.getChatServerPort());
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(chatSocket.getOutputStream());
//            ObjectInputStream objectInputStream = new ObjectInputStream(chatSocket.getInputStream());
//            chatCommunicator = new SocketCommunicator(chatSocket, objectOutputStream, objectInputStream, null);
//
////            objectOutputStream.write(new Integer(ChatServices.JOIN_TEAM_1));
//            objectOutputStream.writeInt((isTeam1) ? ChatServices.JOIN_TEAM_1 : ChatServices.JOIN_TEAM_2);
//            objectOutputStream.flush();
//
////            new ChatListenerThread(chatSocket,objectOutputStream,objectInputStream).start();
//            chatListenerThread = new ChatListenerThread(chatCommunicator, globalChat);
//            chatListenerThread.start();
//        } catch (IOException e) {
//            System.out.println("Cannot create chat socket");
//        }

        Player p = Main.getCommunicator().getPlayer();
        initChatServer(p);
        initWaitintgRoomServer(p);



//        new Thread() {
//            @Override
//            public void run() {
//                while (waiting) {
//                    try {
//                        Thread.sleep(7000);
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//
//                    if(checkBattleStart()) {
//                        toMatchPanel();
//                        break;
//                    }
//
////                    processWaitingState(sc);
////                    System.out.println("THREAD FOR UPDATE SELECTED POKEMON LIST");
//                    g1.clear();
//                    g2.clear();
//                    initSelectedPokeView();
//                }
//            }
//        }.start();
    }

    private void initCreateBtn(final SocketCommunicator sc) {
        remove(createBtn);

        createBtn = new ImageButton(CREATE_X, CREATE_Y, CREATE_W, CREATE_H,
                FileUtility.GS_BTN_IMG, FileUtility.GS_BTN_IMG) {
            @Override
            public void onClick() {
                if (!roomInfo.getHostname().equals(sc.getUsername())) {
                    return;
                }
                startBattle();
            }
        };

        createBtn.setText("Start");
        add(createBtn);
    }

    private void initPokemonList(final SocketCommunicator sc) {
        pokeListView = new ArrayList<PokeForSelectView>();
        ArrayList<Pokemon> list = (ArrayList<Pokemon>) sc.read();

        for (int i = 0, px = SelectedPokeView.SPV_WIDTH + 10, py = BG_Y + 11; i < list.size(); i++) {
            PokeForSelectView pokeItem =
                    new PokeForSelectView(PokemonFactory.getPokeAvatar(list.get(i).getName()), px, py) {
                        @Override
                        public void onClick() {
                            if (selected) {
                                return;
                            }

                            for (PokeForSelectView pfsv : pokeListView) {
                                pfsv.setSelected(false);
                            }
                            selected = true;
                            repaint();
                        }
                    };

            pokeListView.add(pokeItem);
            add(pokeItem);
            px += SelectedPokeView.AVATAR_SIZE + 5;

            if (i + 1 % 5 == 0) {
                px = SelectedPokeView.SPV_WIDTH + 10;
                py += SelectedPokeView.AVATAR_SIZE + 5;
            }
        }
    }

    private void initChatFeature() {
        chatTabs = new JTabbedPane();
        globalChat = new JTextArea();
//        teamChat = new JTextArea();
        chatTyper = new JTextArea();

//        chatTabs.setBounds(CHATBOX_X, CHATBOX_Y, LAYER_W2, CHATBOX_H);
        globalChat.setBounds(CHATBOX_X, CHATBOX_Y, LAYER_W2, CHATBOX_H);
        chatTyper.setBounds(CHATBOX_X, CHATTYPER_Y, LAYER_W2, CHATTYPER_H);

//        chatTabs.addTab("Global", globalChat);
//        chatTabs.addTab("Team", teamChat);

        chatTyper.addKeyListener(this);
        globalChat.setText("");
        globalChat.setEditable(false);
        globalChat.setLineWrap(true);

        add(globalChat);
//        add(chatTabs);
        add(chatTyper);
    }

    private void initChatServer(Player p) {
        try {
            Socket chatSocket = new Socket(Server.IP, roomInfo.getChatServerPort());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(chatSocket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(chatSocket.getInputStream());
            chatCommunicator = new SocketCommunicator(chatSocket, objectOutputStream, objectInputStream, p);

//            objectOutputStream.write(new Integer(ChatServices.JOIN_TEAM_1));
            objectOutputStream.writeObject(p);
            objectOutputStream.writeInt((isTeam1) ? ChatServices.JOIN_TEAM_1 : ChatServices.JOIN_TEAM_2);
            objectOutputStream.flush();

//            new ChatListenerThread(chatSocket,objectOutputStream,objectInputStream).start();
            chatListenerThread = new ChatListenerThread(chatCommunicator, globalChat);
            chatListenerThread.start();
        } catch (IOException e) {
            System.out.println("Cannot create chat socket");
        }
    }

    private void initWaitintgRoomServer(Player p) {
        try {
            Socket roomSocket = new Socket(Server.IP, roomInfo.getRoomServerPort());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(roomSocket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(roomSocket.getInputStream());

            System.out.println("Start creating waitingRoomCommunicator");
            waitingRoomCommunicator = new SocketCommunicator(roomSocket, objectOutputStream, objectInputStream, p);

            waitingRoomCommunicator.write(p);
            waitingRoomCommunicator.write(Main.getCommunicator().getUsername());
            waitingRoomCommunicator.flushOutput();

//            objectOutputStream.writeInt((isTeam1) ? ChatServices.JOIN_TEAM_1 : ChatServices.JOIN_TEAM_2);
//            objectOutputStream.flush();

            System.out.println("waitingRoomCommunicator created");

//            new WaitingRoomListenerThread(chatSocket,objectOutputStream,objectInputStream).start();
            waitingRoomListenerThread = new WaitingRoomListenerThread(this, waitingRoomCommunicator);
            waitingRoomListenerThread.start();

            System.out.println("waitingRoomThread started");
        } catch (IOException e) {
            System.out.println("Cannot create room socket");
        }
    }

    private void initSelectedPokeView() {
        g1 = new ArrayList<SelectedPokeView>();
        g2 = new ArrayList<SelectedPokeView>();
//        SocketCommunicator sc = Main.getCommunicator();
//
//        sc.sendRequestHeader(Services.IN_ROOM_NOTIFY_SELECTED_POK);
//        sc.write(roomInfo.getHostname());
//        sc.flushOutput();
//
//        SelectedPokeInfo[] t1 = (SelectedPokeInfo[]) sc.read();
//        SelectedPokeInfo[] t2 = (SelectedPokeInfo[]) sc.read();
//
//        for (int i = 0, y = BG_Y + 1; i < roomInfo.getPlayersPerTeam(); i++) {
//            g1.add(new SelectedPokeView(t1[i], LEFT_COL_X, y));
//            g2.add(new SelectedPokeView(t2[i], RIGHT_COL_X, y));
//            y += SelectedPokeView.SPV_HEIGHT;
//        }
//
//        repaint();
    }

    public void reinitSelectedPokeView(SelectedPokeInfo[] t1, SelectedPokeInfo[] t2) {
        g1.clear();
        g2.clear();

        for (int i = 0, y = BG_Y + 1; i < roomInfo.getPlayersPerTeam(); i++) {
            g1.add(new SelectedPokeView(t1[i], LEFT_COL_X, y));
            g2.add(new SelectedPokeView(t2[i], RIGHT_COL_X, y));
            y += SelectedPokeView.SPV_HEIGHT;
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawTransparentLayer(g, SelectedPokeView.SPV_WIDTH, BG_Y + 1, LAYER_W2, BG_H);

        for (int i = 0; i < roomInfo.getPlayersPerTeam() && i < g1.size(); i++) {
            g1.get(i).draw(g);
            g2.get(i).draw(g);
        }
//        for(SelectedPokeView spv : g1) {
//            spv.draw(g);
//        }
//        for(SelectedPokeView spv : g2) {
//            spv.draw(g);
//        }

        for (PokeForSelectView p : pokeListView) {
            p.draw(g);
        }
    }

    private void startBattle() {
        SocketCommunicator sc = Main.getCommunicator();
        sc.sendRequestHeader(Services.BATTLE_START);
        sc.flushOutput();

        System.out.println("startBattle request sent");

        int result = (Integer)sc.read();
        System.out.println("startBattle receive result");
//        System.out.println("startBattle start changing panel");
//        try {
//            Thread.sleep(3000);
//        } catch(Exception ex) {
//        }
//        toMatchPanel();
    }

    private boolean checkBattleStart() {
        SocketCommunicator sc = Main.getCommunicator();
        sc.sendRequestHeader(Services.BATTLE_CHECK_STATE);
        sc.write(roomInfo.getHostname());
        sc.flushOutput();

        System.out.println("checkBattleStart request sent");

        try {
            boolean result = (Integer)sc.read() == Services.BATTLE_START;
            return result;
        } catch(Exception ex) {
            return false;
        }
    }

    private void toMatchPanel() {
        System.out.println("toMatchPanel start changing panel");
//        waiting = false;
//        chatCommunicator.close();
        Main.getInstance().setCurrPanel(new MatchPanel(MapUtil.MAP_ARRS[0],
                chatCommunicator, chatListenerThread, isTeam1, roomInfo.getHostname()));
        System.out.println("toMatchPanel end");
    }

//    private void processWaitingState(SocketCommunicator sc) {
////        int response = (Integer)sc.read();
//        System.out.println(sc.getUsername() + " ROOM waiiting for response");
//        try {
//            switch ((Integer) sc.read()) {
//                case Services.GET_IN_ROOM_T1: {
//                    System.out.println("Update selected pokemon team 1");
//                    reinitSelectedPokeView(sc, true);
//                    break;
//                }
//                case Services.GET_IN_ROOM_T2: {
//                    System.out.println("Update selected pokemon team 2");
//                    reinitSelectedPokeView(sc, false);
//                    break;
//                }
//                default: {
//                    System.out.println("Problem in notification");
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        repaint();
//    }

//    private void reinitSelectedPokeView(SocketCommunicator sc, boolean isTeam1) {
//        ArrayList<SelectedPokeView> g = (isTeam1) ? g1 : g2;
//        g.clear();
//
//        sc.sendRequestHeader(Services.IN_ROOM_NOTIFY_SELECTED_POK);
//        sc.write(roomInfo.getHostname());
//        sc.flushOutput();
//
//        System.out.println("Update selected pokemon read pokemon array");
//        SelectedPokeInfo[] t1 = (SelectedPokeInfo[]) sc.read();
//        SelectedPokeInfo[] t2 = (SelectedPokeInfo[]) sc.read();
//        SelectedPokeInfo[] t = (isTeam1) ? t1 : t2;
//
//        int colX = (isTeam1) ? LEFT_COL_X : RIGHT_COL_X;
//
//        for (int i = 0, y = BG_Y + 1; i < roomInfo.getPlayersPerTeam(); i++) {
//            g.add(new SelectedPokeView(t[i], colX, y));
//            y += SelectedPokeView.SPV_HEIGHT;
//        }
//
//        repaint();
//    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            chatCommunicator.write(new Integer(ChatServices.GLOBAL_CHAT));
            chatCommunicator.write(Main.getCommunicator().getUsername() + ": " + chatTyper.getText());
            chatCommunicator.flushOutput();
            chatTyper.setText("");
        }
    }

    @Override
    public void closeSocket() {
        chatCommunicator.close();

//        waitingRoomCommunicator.sendRequestHeader(Services.IN_ROOM_NOTIFY_SELECTED_POK);
//        waitingRoomCommunicator.flushOutput();
        waitingRoomCommunicator.close();
    }

    public RoomPublicInfo getRoomInfo() {
        return roomInfo;
    }
}
