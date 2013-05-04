package database;

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

/**
 * Created with IntelliJ IDEA.
 * User: CHINHNHAN
 * Date: 4/28/13
 * Time: 9:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class PokemonDAOImpl implements PokemonDAO {

    JdbcTemplate jdbcTemplate;

    public PokemonDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createPokemonTable() {
        Resource resource = new ClassPathResource("create_pokemon_table.sql");
        JdbcTestUtils.executeSqlScript(jdbcTemplate, resource, true);
    }

    @Override
    public void addPokemon(Pokemon pokemon, String ownerName) {

        String sql = "insert into Pokemon(PokemonName,Damage,Health,Type,OwnerName) values(?,?,?,?,?)";
        if (getPokemonWithOwnerAndPokemonName(pokemon.getName(), ownerName).size() < 1) {
            int index = jdbcTemplate.update(sql, pokemon.getName(), pokemon.getAtk(),
                    pokemon.getHp(), pokemon.getType(0), ownerName);
            System.out.println(index);
        }


    }

    @Override
    public List<Pokemon> getPokemonWithOwner(String ownerName) {
        System.out.println("Pokemon list is getting");
        String sql = "select * from Pokemon where OwnerName='" + ownerName + "'";

        List<Pokemon> pokemonList = jdbcTemplate.query(sql, new RowMapper<Pokemon>() {
            @Override
            public Pokemon mapRow(ResultSet resultSet, int i) throws SQLException {
                Pokemon p = new Pokemon(resultSet.getString(2), resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getInt(5));

                System.out.println(p.toString());
                return p;
            }
        });

        System.out.println("Pokemon list size 2: " + pokemonList.size());
        return pokemonList;

    }

    @Override
    public List<Pokemon> getPokemonWithOwnerAndPokemonName(String pokemonName, String ownerName) {
        String sql = "select * from Pokemon where OwnerName='" + ownerName + "'" + "and PokemonName='" + pokemonName + "'";
        List<Pokemon> pokemonList = jdbcTemplate.query(sql, new RowMapper<Pokemon>() {
            @Override
            public Pokemon mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Pokemon(resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4));

            }
        });
        return pokemonList;
    }
}
