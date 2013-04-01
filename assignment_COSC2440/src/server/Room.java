package server;

import model.Player;
import server.SocketCommunicator;

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

    public static final int WAITING_STATE = 0;
    public static final int PLAYING_STATE = 1;
    public static final int ENDING_STATE = 2;

    private Map<String, SocketCommunicator> team1;
    private Map<String, SocketCommunicator> team2;
    private String hostName;
    private int state;
    private int type; // Max number of player per team.
    
    public Room(SocketCommunicator host, int type) {
        team1 = new LinkedHashMap<String, SocketCommunicator>();
        team2 = new LinkedHashMap<String, SocketCommunicator>();
        hostName = host.getUsername();
        this.type = type;
        
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
        if(team1.size() <= team2.size()) {
            team1.put(p.getUsername(), p);
            return -1;
        } else {
            team2.put(p.getUsername(), p);
            return -2;
        }
    }

    public void removePlayer(SocketCommunicator p) {
        team1.remove(p.getUsername());
        team2.remove(p.getUsername());
    }

    public Map<String, Player> getPlayerTeam(boolean isTeam1) {
        Map<String, Player> map = new LinkedHashMap<String, Player>();
        Map<String, SocketCommunicator> team = (isTeam1) ? team1 : team2;

        for(String name : team.keySet()) {
            map.put(name, team.get(name).getPlayer());
        }

        return map;
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

    public int getType() {
        return type;
    }
}
