package database;

import model.Player;

import java.util.List;


public interface PlayerDAO {

    public static final String INSERT_CMD = "insert into Account(Username,Password) values(?,?)";

    public void createPlayerTable();
    public int addPlayer(String userName, String password);
    public int addPlayer(Player player);
    public List<Player> getPlayer(String userName);
    public boolean contains(String userName);
    public boolean authentication(String userName, String password);


}
