package database;

import model.Player;
import model.pokemon.Pokemon;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class PlayerDAOImpl implements PlayerDAO {

    private JdbcTemplate jdbcTemplate;

//    public void setDataSource(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }

    public PlayerDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void createPlayerTable() {
        Resource resource = new ClassPathResource("create_account_table.sql");
        JdbcTestUtils.executeSqlScript(jdbcTemplate, resource, true);
    }

    public int addPlayer(String userName, String password) {
        Player player = new Player(userName, password);
        return addPlayer(player);
    }

    @Override
    public int addPlayer(Player player) {
        try {
            jdbcTemplate.update(INSERT_CMD, player.getUsername(), player.getPw());
            PokemonDAOImpl pokemonDAO = DatabaseSpring.getPokemonDAO();

            pokemonDAO.addPokemon(player.getPokemon(0), player.getUsername());
            return 1;
        } catch(Exception ex) {
            System.out.println("addPlayer() - already exist");
            return 0;
        }
    }

    public List<Player> getPlayer(String requestedUserName) {
        String sql = "select * from Account where Username='" + requestedUserName + "'";

        List<Player> accountList = jdbcTemplate.query(sql, new RowMapper<Player>() {
            @Override
            public Player mapRow(ResultSet resultSet, int i) throws SQLException {
//                PokemonDAOImpl pokemonDAO = DatabaseSpring.getPokemonDAO();
                String username = resultSet.getString(1);
                String pw = resultSet.getString(2);

//                List<Pokemon> pokemons = pokemonDAO.getPokemonWithOwner(username);
                Player account = new Player(username, pw, null);

                System.out.println("Username " + username);

                return account;
            }
        });

        PokemonDAOImpl pokemonDAO = DatabaseSpring.getPokemonDAO();
        List<Pokemon> pokemons = pokemonDAO.getPokemonWithOwner(accountList.get(0).getUsername());

        accountList.get(0).setPokemons(pokemons);
        System.out.println("Number of players found: " + accountList.size());
        return accountList;
    }

    @Override
    public boolean contains(String username) {
        String sql = "select * from Account where Username='" + username + "'";

        List<Player> accountList = jdbcTemplate.query(sql, new RowMapper<Player>() {
            @Override
            public Player mapRow(ResultSet resultSet, int i) throws SQLException {
                String username = resultSet.getString(1);
                String pw = resultSet.getString(2);

                Player account = new Player(username, pw, null);

                System.out.println("Username " + username);

                return account;
            }
        });

        System.out.println("Number of players found: " + accountList.size());
        return !accountList.isEmpty();
    }

    @Override
    public boolean authentication(String userName, String password) {
        List<Player> accountList = getPlayer(userName);
        if(accountList.isEmpty()) {
            return false;
        }
        if (accountList.get(0).getPw().equals(password)) {
            return true;
        }
        return false;


    }
}
