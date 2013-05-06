package server;

import model.Player;
import model.pokemon.*;
import server.battlehandler.BattleServer;
import server.chathandler.ChatServer;
import server.waitingroomhandler.WaitingRoomServer;
import utility.MoveUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: s3342128
 * Date: 29/03/13
 * Time: 9:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class Room {

    public static final int TEAM_1 = -1;
    public static final int TEAM_2 = -2;
    public static final int ALL_PLAYERS = -3;
    public static final int ADD_PLAYER_FAILED = 0;
    public static final int REMOVE_ONLY_PLAYER = 1;
    public static final int REMOVE_PLAYER_AND_ROOM = 2;
    public static final int REMOVE_PLAYER_AND_CHANGE_HOST = 3;
    public static final int WAITING_STATE = 0;
    public static final int PLAYING_STATE = 1;
    public static final int ENDING_STATE = 2;
    private ChatServer chatServer;
    private int chatServerPort;
    private WaitingRoomServer waitingRoomServer;
    private BattleServer battleServer;
    private int roomServerPort;
    //    private int battleServerPort;
    private Map<String, SocketCommunicator> team1;
    private Map<String, SocketCommunicator> team2;
    private ArrayList<SocketCommunicator> orderOfPlayers;
    private String hostName;
    private PokeInBattleInfo[] pokeInBattle1;
    private PokeInBattleInfo[] pokeInBattle2;
    private int state;
    private int numPlayersPerTeam; // Max number of player per team.
    private int currentTurn;

    public Room(SocketCommunicator host, int type) {
        team1 = new LinkedHashMap<String, SocketCommunicator>();
        team2 = new LinkedHashMap<String, SocketCommunicator>();
        hostName = host.getUsername();
        this.numPlayersPerTeam = type;

        team1.put(hostName, host);
        ///////////////////////
//        Server.makeNewPort();
//        chatServerPort = (Integer) Server.USING_PORT.toArray()[Server.USING_PORT.size() - 1];
        chatServerPort = Server.makeNewPort();
        chatServer = new ChatServer(chatServerPort);
        chatServer.start();
        ///////////////////////

        roomServerPort = Server.makeNewPort();
        waitingRoomServer = new WaitingRoomServer(roomServerPort);
        waitingRoomServer.start();
    }

//    @Override
//    public void run() {
//        while(true) {
//            switch(state) {
//                case WAITING_STATE:
//                    processWaitingState();
//                    break;
//                case PLAYING_STATE:
//                    processPlayingState() ;
//                    break;
//                case ENDING_STATE:
//                    processEndingState();
//                    return;
//            }
//        }
//    }

//    private void processWaitingState() {
//
//    }
//
//    private void processPlayingState() {
//
//    }
//
//    private void processEndingState() {
//
//    }

    public int addPlayer(SocketCommunicator p) {
        if (team1.size() >= numPlayersPerTeam && team2.size() >= numPlayersPerTeam) {
            return ADD_PLAYER_FAILED;
        }

        if (team1.size() < numPlayersPerTeam && team2.size() >= numPlayersPerTeam) {
            team1.put(p.getUsername(), p);
            return TEAM_1;
        }

        if (team1.size() >= numPlayersPerTeam && team2.size() < numPlayersPerTeam) {
            team2.put(p.getUsername(), p);
            return TEAM_2;
        }

        if (team1.size() <= team2.size()) {
            team1.put(p.getUsername(), p);
            return TEAM_1;
        } else {
            team2.put(p.getUsername(), p);
            return TEAM_2;
        }
    }

    public int removePlayer(SocketCommunicator p) {
        int result;

        System.out.println("removePlayer - begin");

        if (p.getUsername().equals(hostName)) {
            System.out.println("removePlayer - need change host in room");
            result = changeRoomHost(p);
        } else {
            System.out.println("removePlayer - only remove player");
            team1.remove(p.getUsername());
            team2.remove(p.getUsername());

            System.out.println("removePlayer - Stopping player sockets");
            stopPlayerSocket(p.getUsername());
            System.out.println("removePlayer - Player sockets has been stopped");

            result = REMOVE_ONLY_PLAYER;
        }

        System.out.println("Notify other players after a player logged out");
        waitingRoomServer.notifyPlayers(getSelectedPokeInfoTeam(Room.TEAM_1), getSelectedPokeInfoTeam(Room.TEAM_2));
        System.out.println("Finish notifying");

        return result;
    }

    public Map<String, Player> getPlayerTeam(boolean isTeam1) {
        Map<String, Player> map = new LinkedHashMap<String, Player>();
        Map<String, SocketCommunicator> team = (isTeam1) ? team1 : team2;

        for (String name : team.keySet()) {
            map.put(name, team.get(name).getPlayer());
        }

        return map;
    }

    public int changeRoomHost(SocketCommunicator p) {
        Map<String, SocketCommunicator> team = (team1.containsKey(p.getUsername())) ? team1 : team2;
        Map<String, SocketCommunicator> otherTeam = (team == team1) ? team2 : team1;

        stopPlayerSocket(p.getUsername());
        team.remove(p.getUsername());

        if (team.size() > 1) {
            hostName = ((SocketCommunicator) (team.values().toArray()[0])).getUsername();
            return REMOVE_PLAYER_AND_CHANGE_HOST;
        } else if (!otherTeam.isEmpty()) {
            hostName = ((SocketCommunicator) (otherTeam.values().toArray()[0])).getUsername();
            return REMOVE_PLAYER_AND_CHANGE_HOST;
        } else {
            return REMOVE_PLAYER_AND_ROOM;
        }
    }

    private void stopPlayerSocket(String username) {
        chatServer.closePlayerSocket(username);
        waitingRoomServer.closePlayerSocket(username);
    }

    public void notifyAllPlayers(int msg, SocketCommunicator except) {
        notifyTeam1(msg, except);
        notifyTeam2(msg, except);
    }

    public void notifyTeam1(int msg, SocketCommunicator except) {
        System.out.println("Notify Team 1");
        for (SocketCommunicator sc : team1.values()) {
            if (sc == except) {
                continue;
            }
            sc.write(new Integer(msg));
            sc.flushOutput();
        }
    }

    public void notifyTeam2(int msg, SocketCommunicator except) {
        System.out.println("Notify Team 2");
        for (SocketCommunicator sc : team2.values()) {
            if (sc == except) {
                System.out.println("Skip notify for " + sc.getUsername());
                continue;
            }
            sc.write(new Integer(msg));
            sc.flushOutput();
        }
    }

    public void notifySelectedPoke() {
        for (SocketCommunicator sc : team1.values()) {
            sc.write(getSelectedPokeInfoTeam(Room.TEAM_1));
            sc.write(getSelectedPokeInfoTeam(Room.TEAM_2));
            sc.flushOutput();
        }

        for (SocketCommunicator sc : team2.values()) {
            sc.write(getSelectedPokeInfoTeam(Room.TEAM_1));
            sc.write(getSelectedPokeInfoTeam(Room.TEAM_2));
            sc.flushOutput();
        }
    }

    public SelectedPokeInfo[] getSelectedPokeInfoTeam(int teamNo) {
        Map<String, SocketCommunicator> team = (teamNo == TEAM_1) ? team1 : team2;
        SelectedPokeInfo[] spi = new SelectedPokeInfo[numPlayersPerTeam];
        ArrayList<SocketCommunicator> t = new ArrayList<SocketCommunicator>(team.values());

        for (int i = 0; i < t.size(); i++) {
            Player p = t.get(i).getPlayer();
            spi[i] = p.makeSelectedPokeInfo(p.getUsername().equals(hostName));
        }

        return spi;
    }

    public void generatePlayerOrder() {
//        ArrayList<SocketCommunicator> t1 = new ArrayList<SocketCommunicator>(team1.values());
//        ArrayList<SocketCommunicator> t2 = new ArrayList<SocketCommunicator>(team2.values());
//        pokeInBattle1 = new PokeInBattleInfo[team1.size()];
//        pokeInBattle2 = new PokeInBattleInfo[team2.size()];
//        orderOfPlayers = new ArrayList<SocketCommunicator>();

//        for (int i = 0; i < t1.size(); i++) {
//            SocketCommunicator sc = t1.get(i);
//            orderOfPlayers.add(sc);
//            String pokeName = sc.getPlayer().getSelectedPoke().getName();
//            pokeInBattle1[i] = new PokeInBattleInfo(sc.getUsername(),
//                    PokemonFactory.getPokeIcon(pokeName), MoveUtil.START_POSITIONS_T1[i]);
//        }
//
//        for (int i = 0; i < t2.size(); i++) {
//            SocketCommunicator sc = t2.get(i);
//            orderOfPlayers.add(sc);
//            String pokeName = sc.getPlayer().getSelectedPoke().getName();
//            pokeInBattle2[i] = new PokeInBattleInfo(sc.getUsername(),
//                    PokemonFactory.getPokeIcon(pokeName), MoveUtil.START_POSITIONS_T2[i]);
//        }


//        for(int i=0; i<team1.size() || i<team2.size(); i++) {
//            if(i<team1.size()) {
//                SocketCommunicator sc = t1.get(i);
//                orderOfPlayers.add(sc);
//                String pokeName = sc.getPlayer().getSelectedPoke().getName();
//                pokeInBattle1[i] = new PokeInBattleInfo(sc.getUsername(),
//                        PokemonFactory.getPokeIcon(pokeName), MoveUtil.START_POSITIONS_T1[i]);
//            }
//
//            if(i<team2.size()) {
//                SocketCommunicator sc = t2.get(i);
//                orderOfPlayers.add(sc);
//                String pokeName = sc.getPlayer().getSelectedPoke().getName();
//                pokeInBattle2[i] = new PokeInBattleInfo(sc.getUsername(),
//                        PokemonFactory.getPokeIcon(pokeName), MoveUtil.START_POSITIONS_T2[i]);
//            }
//        }
//
//        currentTurn = 0;


//        initializeForBattle(t1, t2);
    }

    public SocketCommunicator nextTurn() {
        if (currentTurn < orderOfPlayers.size() - 1) {
            return orderOfPlayers.get(++currentTurn);
        } else {
            currentTurn = 0;
            return orderOfPlayers.get(currentTurn);
        }
    }

    public void initializeBattle() {
        System.out.println("Room initializing battle");

        pokeInBattle1 = new PokeInBattleInfo[team1.size()];
        pokeInBattle2 = new PokeInBattleInfo[team2.size()];
        ArrayList<SocketCommunicator> t1 = new ArrayList<SocketCommunicator>(team1.values());
        ArrayList<SocketCommunicator> t2 = new ArrayList<SocketCommunicator>(team2.values());

        System.out.println("Room arrays created");

        for (int i = 0; i < pokeInBattle1.length; i++) {
            SocketCommunicator sc = t1.get(i);
            Pokemon pokemon = sc.getPlayer().getSelectedPoke();
            String pokeName = pokemon.getName();

            pokeInBattle1[i] = new PokeInBattleInfo(sc.getUsername(),
                    PokemonFactory.getPokeIconURL(pokeName), MoveUtil.START_POSITIONS_T1[i], TEAM_1, pokemon.getHp());
        }
        for (int i = 0; i < pokeInBattle2.length; i++) {
            SocketCommunicator sc = t2.get(i);
            Pokemon pokemon = sc.getPlayer().getSelectedPoke();
            String pokeName = pokemon.getName();
            pokeInBattle2[i] = new PokeInBattleInfo(sc.getUsername(),
                    PokemonFactory.getPokeIconURL(pokeName), MoveUtil.START_POSITIONS_T2[i], TEAM_2, pokemon.getHp());
        }

        orderOfPlayers = new ArrayList<SocketCommunicator>();

        for (int i = 0; i < team1.size() || i < team2.size(); i++) {
            if (i < team1.size()) {
                orderOfPlayers.add(t1.get(i));
            }
            if (i < team2.size()) {
                orderOfPlayers.add(t2.get(i));
            }
        }

        currentTurn = 0;

        System.out.println("Room Finish init battle");
    }

    public PokeInBattleInfo getPokeInBattle(String owner) {
        for (PokeInBattleInfo p : pokeInBattle1) {
            if (p.getOwner().equals(owner)) {
                return p;
            }
        }
        for (PokeInBattleInfo p : pokeInBattle2) {
            if (p.getOwner().equals(owner)) {
                return p;
            }
        }
        return null;
    }

    public void startBattle() {
//        if (waitingRoomServer != null) {
//            waitingRoomServer.stopThread();
        waitingRoomServer.startBattle();
//            waitingRoomServer = null;
//        }
        System.out.println("Room waitingRoomServer stopped");
        battleServer = new BattleServer(this, roomServerPort);
        battleServer.start();

        System.out.println("Room Battle started");
    }

    public String[] attack(PokeInBattleRequest request) {
        SocketCommunicator atkSc = getCurrentPlayer();
        Pokemon atkPoke = atkSc.getPlayer().getSelectedPoke();

        Map<String, SocketCommunicator> enemyTeam = (request.getTeam() == TEAM_1) ? team2 : team1;

        SocketCommunicator enemy = enemyTeam.get(request.getEnemyName());
        Pokemon enemyPoke = enemy.getPlayer().getSelectedPoke();

        PokeInBattleInfo[] enemyTeamInfo = (request.getTeam() == TEAM_1) ?
                request.getPokeModels2() : request.getPokeModels1();
        PokeInBattleInfo enemyInfo = null;

        for (PokeInBattleInfo p : enemyTeamInfo) {
            if (p.getOwner().equals(enemy.getUsername())) {
                enemyInfo = p;
                break;
            }
        }

        enemyInfo.setHp(enemyInfo.getHp()
                - PokeUtil.calculateDamage(SkillFactory.makeDefaultSkill(atkPoke.getType(0)), atkPoke, enemyPoke));

        System.out.println("Room " + enemyInfo.getOwner() + "->" + enemyInfo.getHp() + "-> " + enemyInfo.isDead());

        return new String[]{atkPoke.getName(), enemyPoke.getName()};
    }

    public void close() {
        chatServer.stopThread();

        if (waitingRoomServer != null) {
            waitingRoomServer.stopThread();
        }

        if (battleServer != null) {
            battleServer.stopThread();
        }
    }

    public boolean isEmpty() {
        return team1.isEmpty() && team2.isEmpty();
    }

    public Map<String, SocketCommunicator> getTeam1() {
        return team1;
    }

    public Map<String, SocketCommunicator> getTeam2() {
        return team2;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getNumPlayersPerTeam() {
        return numPlayersPerTeam;
    }

    public ChatServer getChatServer() {
        return chatServer;
    }

    public int getChatServerPort() {
        return chatServerPort;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public SocketCommunicator getCurrentPlayer() {
        return orderOfPlayers.get(currentTurn);
    }

    public PokeInBattleInfo[] getPokeInBattle1() {
        return pokeInBattle1;
    }

    public PokeInBattleInfo[] getPokeInBattle2() {
        return pokeInBattle2;
    }

    public int getRoomServerPort() {
        return roomServerPort;
    }
}
