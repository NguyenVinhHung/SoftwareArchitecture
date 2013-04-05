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

    public RoomPublicInfo(String roomNo, int playersPerTeam, String hostname) {
        this.roomNo = roomNo;
        this.type = playersPerTeam + " Vs " + playersPerTeam;
        this.hostname = hostname;
        this.playersPerTeam = playersPerTeam;
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
}
