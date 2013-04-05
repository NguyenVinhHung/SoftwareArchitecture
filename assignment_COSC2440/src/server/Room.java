package server;

import model.Player;
import model.pokemon.SelectedPokeInfo;

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
public class Room implements Runnable {

    public static final int TEAM_1 = -1;
    public static final int TEAM_2 = -2;
    public static final int ALL_PLAYERS = -3;
    public static final int ADD_PLAYER_FAILED = 0;
    public static final int REMOVE_ONLY_PLAYER = 1;
    public static final int REMOVE_PLAYER_AND_ROOM = 1;

    public static final int WAITING_STATE = 0;
    public static final int PLAYING_STATE = 1;
    public static final int ENDING_STATE = 2;

    private Map<String, SocketCommunicator> team1;
    private Map<String, SocketCommunicator> team2;
    private String hostName;
    private int state;
    private int numPlayersPerTeam; // Max number of player per team.
    
    public Room(SocketCommunicator host, int type) {
        team1 = new LinkedHashMap<String, SocketCommunicator>();
        team2 = new LinkedHashMap<String, SocketCommunicator>();
        hostName = host.getUsername();
        this.numPlayersPerTeam = type;
        
        team1.put(hostName, host);
    }

    @Override
    public void run() {
        while(true) {
            switch(state) {
                case WAITING_STATE:
                    processWaitingState();
                    break;
                case PLAYING_STATE:
                    processPlayingState() ;
                    break;
                case ENDING_STATE:
                    processEndingState();
                    return;
            }
        }
    }

    private void processWaitingState() {

    }

    private void processPlayingState() {

    }

    private void processEndingState() {

    }

    public int addPlayer(SocketCommunicator p) {
        if(team1.size()>=numPlayersPerTeam && team2.size()>=numPlayersPerTeam) {
            return ADD_PLAYER_FAILED;
        }

        if(team1.size()<numPlayersPerTeam && team2.size()>=numPlayersPerTeam) {
            team1.put(p.getUsername(), p);
            return TEAM_1;
        }

        if(team1.size()>=numPlayersPerTeam && team2.size()<numPlayersPerTeam) {
            team2.put(p.getUsername(), p);
            return TEAM_2;
        }

        if(team1.size() <= team2.size()) {
            team1.put(p.getUsername(), p);
            return TEAM_1;
        } else {
            team2.put(p.getUsername(), p);
            return TEAM_2;
        }
    }

    public int removePlayer(SocketCommunicator p) {
        if(p.getUsername().equals(hostName)) {
            return changeRoomHost(p);
        } else {
            team1.remove(p.getUsername());
            team2.remove(p.getUsername());
            return REMOVE_ONLY_PLAYER;
        }
    }

    public Map<String, Player> getPlayerTeam(boolean isTeam1) {
        Map<String, Player> map = new LinkedHashMap<String, Player>();
        Map<String, SocketCommunicator> team = (isTeam1) ? team1 : team2;

        for(String name : team.keySet()) {
            map.put(name, team.get(name).getPlayer());
        }

        return map;
    }

    public int changeRoomHost(SocketCommunicator p) {
        Map<String, SocketCommunicator> team = (team1.containsKey(p.getUsername())) ? team1 : team2;
        Map<String, SocketCommunicator> otherTeam = (team==team1) ? team2 : team1;

        if(team.size() > 1) {
            team.remove(p.getUsername());
            hostName = ((SocketCommunicator)(team.values().toArray()[0])).getUsername();
            return REMOVE_ONLY_PLAYER;
        } else if(!otherTeam.isEmpty()) {
            team.remove(p.getUsername());
            hostName = ((SocketCommunicator)(otherTeam.values().toArray()[0])).getUsername();
            return REMOVE_ONLY_PLAYER;
        } else {
            team.remove(p.getUsername());
            return REMOVE_PLAYER_AND_ROOM;
        }
    }

    public void notifyAllPlayers(int msg) {
        notifyTeam1(msg);
        notifyTeam2(msg);
    }

    public void notifyTeam1(int msg) {
        for(SocketCommunicator sc : team1.values()) {
            sc.sendRequestHeader(msg);
            sc.flushOutput();
        }
    }

    public void notifyTeam2(int msg) {
        for(SocketCommunicator sc : team2.values()) {
            sc.sendRequestHeader(msg);
            sc.flushOutput();
        }
    }

    public SelectedPokeInfo[] getSelectedPokeInfoTeam(int teamNo) {
        Map<String, SocketCommunicator> team = (teamNo==TEAM_1) ? team1 : team2;
        SelectedPokeInfo[] spi = new SelectedPokeInfo[numPlayersPerTeam];
        ArrayList<SocketCommunicator> t = new ArrayList<SocketCommunicator>(team.values());

        for(int i=0; i<t.size(); i++) {
            Player p = t.get(i).getPlayer();
            spi[i] = p.makeSelectedPokeInfo(p.getUsername().equals(hostName));
        }

        return spi;
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
}
