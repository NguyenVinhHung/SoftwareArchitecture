package database;

import model.pokemon.Pokemon;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: CHINHNHAN
 * Date: 4/28/13
 * Time: 9:46 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PokemonDAO {

    public void createPokemonTable();
    public void addPokemon(Pokemon pokemon, String ownerName);
    public List<Pokemon> getPokemonWithOwner(String ownerName);
    public List<Pokemon> getPokemonWithOwnerAndPokemonName(String pokemonName, String ownerName);
}
