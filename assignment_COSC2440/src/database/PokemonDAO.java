package database;

import model.pokemon.Pokemon;

import java.util.List;


public interface PokemonDAO {

    public void createPokemonTable();
    public void addPokemon(Pokemon pokemon, String ownerName);
    public List<Pokemon> getPokemonWithOwner(String ownerName);
    public List<Pokemon> getPokemonWithOwnerAndPokemonName(String pokemonName, String ownerName);
}
