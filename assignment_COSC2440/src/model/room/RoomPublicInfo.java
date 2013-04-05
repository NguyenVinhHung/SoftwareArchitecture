package model.room;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/31/13
 * Time: 8:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class RoomPublicInfo implements Serializable {

    private static final long serialVersionUID = 123456789101112131L;

    private String roomNo;
    private String type;
    private String hostname;
    private int playersPerTeam;
    private int chatServerPort;
    
    public RoomPublicInfo(String roomNo, int playersPerTeam, String hostname, int chatServerPort) {
        this.roomNo = roomNo;
        this.type = playersPerTeam + " Vs " + playersPerTeam;
        this.hostname = hostname;
        this.playersPerTeam = playersPerTeam;
        this.chatServerPort = chatServerPort;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public String getType() {
        return type;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPlayersPerTeam() {
        return playersPerTeam;
    }

    @Override
    public String toString() {
        return roomNo + " - " + type + " - " + hostname;
    }

    public int getChatServerPort() {
        return chatServerPort;
}

    public void setChatServerPort(int chatServerPort) {
        this.chatServerPort = chatServerPort;
    }
}
