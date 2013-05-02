package database;

import model.Player;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: CHINHNHAN
 * Date: 4/27/13
 * Time: 3:39 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PlayerDAO {

    public static final String INSERT_CMD = "insert into Account(Username,Password) values(?,?)";

    public void createPlayerTable();
    public int addPlayer(String userName, String password);
    public int addPlayer(Player player);
    public List<Player> getPlayer(String userName);
    public boolean contains(String userName);
    public boolean authentication(String userName, String password);


}
