package model;

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

    private Map<String, Player> team1;
    private Map<String, Player> team2;
    private String hostName;
    private int state;
    
    public Room(Player host) {
        team1 = new LinkedHashMap<String, Player>();
        team2 = new LinkedHashMap<String, Player>();
        hostName = host.getUsername();
        
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

    public void addPlayer(Player p) {
        if(team1.size() <= team2.size()) {
            team1.put(p.getUsername(), p);
        } else {
            team2.put(p.getUsername(), p);
        }
    }

    public Map<String, Player> getTeam1() {
        return team1;
    }

    public Map<String, Player> getTeam2() {
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
}
