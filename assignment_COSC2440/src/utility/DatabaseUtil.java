package utility;

import model.Player;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/30/13
 * Time: 10:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseUtil {

    public static final String SAVE_PATH = "src/data/save.bin";

    private static Map<String, Player> players = new LinkedHashMap<String, Player>();

    public static boolean save() {
        try {
            // Open the file
            File file = new File(SAVE_PATH);
            file.createNewFile();

            if (file.exists()) {
                FileOutputStream out = new FileOutputStream(file);
                ObjectOutputStream output = new ObjectOutputStream(out);

                // Write the object to the file
                output.writeObject(players);
                output.close();
                return true;
            }
        } catch (IOException ex) {
            // Error while saving data
            ex.printStackTrace();
            System.out.println("ERROR WHILE SAVING\n IOException");
        }
        return false;
    }

    public static Map<String, Player> load() {
        try {
            // Open the file
            File file = new File(SAVE_PATH);

            // Check if the file exist
            if (file.exists()) {
                try {
                    FileInputStream in = new FileInputStream(file);
                    ObjectInputStream input = new ObjectInputStream(in);

                    // Return object writen in the file
                    players = (Map<String, Player>) input.readObject();
                    return players;
                } catch (Exception e) {
                    // Error in reading file
                    e.printStackTrace();
                    System.out.println("ERROR LOADING SAVED FILE");
                    return null;
                }
            }
        }
        catch (Exception ex) {

        }
        // Error in reading file
        return null;
    }

    public static Player authorizePlayer(String username, String pw) {
        Player p = players.get(username);
        if(p==null || !p.getPw().equals(pw)) {
             return null;
        }
        return p;
    }

    public static boolean containPlayer(String username) {
        return players.containsKey(username);
    }

    public static Player addPlayer(String username, String pw) {
        if(players.containsKey(username)) {
            return null;
        }

        Player p = new Player(username, pw);
        players.put(username, p);

        return p;
    }

//    public static Player getPlayer(String username) {
//        return players.get(username);
//    }

    public static Map<String, Player> getPlayers() {
        return players;
    }
}
