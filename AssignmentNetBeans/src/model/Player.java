package model;

import model.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: s3342128
 * Date: 29/03/13
 * Time: 9:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class Player {

    private String username;
    private String pw;
    private List<Pokemon> pokemons;

    public Player(String username, String pw) {
        this.username = username;
        this.pw = pw;
        pokemons = new ArrayList<Pokemon>();
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
}
