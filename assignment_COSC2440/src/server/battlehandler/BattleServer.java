package server.battlehandler;

import model.pokemon.PokeAttackResponse;
import model.pokemon.PokeInBattleInfo;
import model.pokemon.PokeInBattleRequest;
import server.*;
import server.chathandler.ChatServices;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: s3342128
 * Date: 16/04/13
 * Time: 1:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class BattleServer extends Thread {

    private final Vector<BattleThread> team1Threads = new Vector<BattleThread>();
    private final Vector<BattleThread> team2Threads = new Vector<BattleThread>();

    private ServerSocket serverSocket;
    private Room room;
    private int port;
    private int actionPoint;
    private boolean running;

    public BattleServer(Room r, int port) {
        this.port = port;
        room = r;
        actionPoint = 2;

        try {
            serverSocket = new ServerSocket(port);
        } catch(IOException ex) {
        }
    }

    @Override
    public void run() {
        running = true;

        try {
            while(running) {
                System.out.println("BatttleServer wait for socket");
                Socket socket = serverSocket.accept();

                System.out.println("BattleServer received socket");

                SocketCommunicator communicator = new SocketCommunicator(socket);
//                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
//                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//                SocketCommunicator communicator = new SocketCommunicator(socket,
//                        objectOutputStream, objectInputStream, null);

                System.out.println("BattleServer communicator created");

                BattleThread battleThread = new BattleThread(communicator, this);

                // Get user team
                int team1Orteam2 = communicator.getFrom().readInt();

                if (team1Orteam2 == ChatServices.JOIN_TEAM_1) {
                    team1Threads.add(battleThread);
                } else {
                    team2Threads.add(battleThread);
                }

                // Start Thread
                new Thread(battleThread).start();
            }
        } catch (Exception ex) {
        }
    }

    public void initBattle() {
        System.out.println("BattleServer Start initializing battle");

        room.initializeBattle();
        notifyPokeInBattleToPlayers(Services.BATTLE_INITIALIZATION, room.getPokeInBattle1(), room.getPokeInBattle2());

        System.out.println("BattleServer Finish init battle");
    }

    public <T extends Serializable> void notifyPlayers(int response, T obj) {
        System.out.println("BattleServer start notifying Players");

        for (int i = 0; i < team1Threads.size(); i++) {
            SocketCommunicator sc = team1Threads.get(i).getCommunicator();
            sc.sendRequestHeader(response);
            sc.write(obj);
            sc.flushOutput();
        }

        for (int i = 0; i < team2Threads.size(); i++) {
            SocketCommunicator sc = team2Threads.get(i).getCommunicator();
            sc.sendRequestHeader(response);
            sc.write(obj);
            sc.flushOutput();
        }

        System.out.println("BattleServer finish notifying Players");
    }

    public <T extends Serializable> void notifyPlayersWithCurrPlayerName(int response, T obj) {
        System.out.println("BattleServer start notifying Players");

        for (int i = 0; i < team1Threads.size(); i++) {
            SocketCommunicator sc = team1Threads.get(i).getCommunicator();
            sc.sendRequestHeader(response);
            sc.write(room.getCurrentPlayer().getUsername());
            sc.write(obj);
            sc.flushOutput();
        }

        for (int i = 0; i < team2Threads.size(); i++) {
            SocketCommunicator sc = team2Threads.get(i).getCommunicator();
            sc.sendRequestHeader(response);
            sc.write(room.getCurrentPlayer().getUsername());
            sc.write(obj);
            sc.flushOutput();
        }

        System.out.println("BattleServer finish notifying Players");
    }

    public void notifyPokeInBattleToPlayers(int response, PokeInBattleInfo[] t1, PokeInBattleInfo[] t2) {
        System.out.println("BattleServer start notifying Players");
        System.out.println("BattleServer PokeInBattleInfo 1 " + t1.length);
        System.out.println("BattleServer PokeInBattleInfo 2 " + t2.length);

        for (int i = 0; i < team1Threads.size(); i++) {
            SocketCommunicator sc = team1Threads.get(i).getCommunicator();
            sc.sendRequestHeader(response);
            sc.write(room.getCurrentPlayer().getUsername());
            sc.write(t1);
            sc.write(t2);
            sc.flushOutput();
        }

        for (int i = 0; i < team2Threads.size(); i++) {
            SocketCommunicator sc = team2Threads.get(i).getCommunicator();
            sc.sendRequestHeader(response);
            sc.write(room.getCurrentPlayer().getUsername());
            sc.write(t1);
            sc.write(t2);
            sc.flushOutput();
        }

        System.out.println("BattleServer finish notifying Players");
    }

    public void notifyPlayers(int msg) {
        for (int i = 0; i < team1Threads.size(); i++) {
            SocketCommunicator sc = team1Threads.get(i).getCommunicator();
            sc.sendRequestHeader(msg);
            sc.flushOutput();
        }

        for (int i = 0; i < team2Threads.size(); i++) {
            SocketCommunicator sc = team2Threads.get(i).getCommunicator();
            sc.sendRequestHeader(msg);
            sc.flushOutput();
        }
    }

    public void attack(PokeInBattleRequest request) {
        String[] result = room.attack(request);

        if(result.length > 1) {
            System.out.println(result[0] + " - " + result[1]);
    //        notifyPokeInBattleToPlayers(Services.BATTLE_ATK, request.getPokeModels1(), request.getPokeModels2());

            notifyPlayers(Services.BATTLE_ATK, new PokeAttackResponse(request.getPokeModels1(), request.getPokeModels2(),
                    result[0], result[1]));
        } else {
            notifyPlayers(Services.BATTLE_FINISH, new Integer(Integer.parseInt(result[0])));

            OnlinePlayerList opl = (OnlinePlayerList)ServerSpring.getBean("onlinePlayerList");
            opl.removeRoom(room.getHostName());
        }
    }

    public void calculateActionPoint() {
        --actionPoint;

        System.out.println("ACTION POINT " + actionPoint);

        if(actionPoint == 0) {
            actionPoint = 2;
            endTurn();
        }
    }

    public void endTurn() {
        String currTurnName = room.nextTurn().getUsername();
        notifyPlayers(Services.BATTLE_END_TURN, currTurnName);
    }

    public void stopThread() {
        try {
            for (int i = 0; i < team1Threads.size(); i++) {
                team1Threads.get(i).stopThread();
            }

            for (int i = 0; i < team2Threads.size(); i++) {
                team2Threads.get(i).stopThread();
            }
            serverSocket.close();
            Server.USING_PORT.remove(port);

        } catch (IOException e) {
            System.out.println("Close the connection ");
        }
    }
}
