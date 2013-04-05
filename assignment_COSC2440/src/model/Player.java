package model;

import model.pokemon.Pokemon;
import model.pokemon.PokemonFactory;
import model.pokemon.SelectedPokeInfo;

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

        // Randomly generate a starter pokemon.
        Random r = new Random(Calendar.getInstance().getTimeInMillis());
        switch(r.nextInt(3)) {
            case 0: pokemons.add(PokemonFactory.makeBlastoise()); break;
            case 1: pokemons.add(PokemonFactory.makeCharizard()); break;
            case 2: pokemons.add(PokemonFactory.makeVenusaur()); break;
        }

//        selectedPoke = pokemons.get(r.nextInt(3));
        selectedPoke = pokemons.get(0);

        if(selectedPoke == null) {
            System.out.println("Initialize Selected pokemon is null");
        } else {
            System.out.println("Initialize Selected pokemon is " + selectedPoke.getName());
        }
    }

    public SelectedPokeInfo makeSelectedPokeInfo(boolean host) {
        if(selectedPoke == null) {
            System.out.println("Selected pokemon is null");
            return null;
        }
        return new SelectedPokeInfo(selectedPoke.getName(), selectedPoke.getLv(), username, host);
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
        System.out.println("Pokemon list size: " + pokemons.size());
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
