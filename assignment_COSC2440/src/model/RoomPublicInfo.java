package model;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/31/13
 * Time: 8:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class RoomPublicInfo {

    private String roomNo;
    private String type;
    private String hostname;

    public RoomPublicInfo(String roomNo, String type, String hostname) {
        this.roomNo = roomNo;
        this.type = type;
        this.hostname = hostname;
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
}
