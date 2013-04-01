package model;

import model.pokemon.Pokemon;
import model.pokemon.PokemonFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: s3342128
 * Date: 29/03/13
 * Time: 9:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class Player implements Serializable {

    private static final long serialVersionUID = 1111111111111111111L;

    private Pokemon selectedPoke;
    private String username;
    private String pw;
    private List<Pokemon> pokemons;

    public Player(String username, String pw) {
        this.username = username;
        this.pw = pw;
        pokemons = new ArrayList<Pokemon>();

        pokemons.add(PokemonFactory.makeBlastoise());
        pokemons.add(PokemonFactory.makeCharizard());
        pokemons.add(PokemonFactory.makeVenusaur());

        Random r = new Random(Calendar.getInstance().getTimeInMillis());
        selectedPoke = pokemons.get(r.nextInt(3));
    }

    public String getUsername() {
        return username;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public Pokemon getPokemon(int idx) {
        return pokemons.get(idx);
    }

    public Pokemon getSelectedPoke() {
        return selectedPoke;
    }

    public void setSelectedPoke(Pokemon selectedPoke) {
        this.selectedPoke = selectedPoke;
    }
}
