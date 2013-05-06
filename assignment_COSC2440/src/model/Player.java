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

        int randPoke = r.nextInt(130) + 1;
        if (randPoke <= 10) {
            pokemons.add(PokemonFactory.makeBlastoise());
        } else if (randPoke > 10 && randPoke <= 20) {
            pokemons.add(PokemonFactory.makeCharizard());
        } else if (randPoke > 20 && randPoke <= 30) {
            pokemons.add(PokemonFactory.makeVenusaur());
        } else if (randPoke > 30 && randPoke <= 40) {
            pokemons.add(PokemonFactory.makeArbok());
        } else if (randPoke > 40 && randPoke <= 50) {
            pokemons.add(PokemonFactory.makeArticuno());
        } else if (randPoke > 50 && randPoke <= 60) {
            pokemons.add(PokemonFactory.makeZapdos());
        } else if (randPoke > 60 && randPoke <= 70) {
            pokemons.add(PokemonFactory.makeBeedrill());
        } else if (randPoke > 70 && randPoke <= 80) {
            pokemons.add(PokemonFactory.makeRaticate());
        } else if (randPoke > 80 && randPoke <= 90) {
            pokemons.add(PokemonFactory.makeButterfree());
        } else if (randPoke > 90 && randPoke <= 100) {
            pokemons.add(PokemonFactory.makeDragonite());
        } else if (randPoke > 100 && randPoke <= 110) {
            pokemons.add(PokemonFactory.makeMortress());
        } else if (randPoke > 110 && randPoke <= 120) {
            pokemons.add(PokemonFactory.makePidgeot());
        } else if (randPoke > 120 && randPoke <= 130) {
            pokemons.add(PokemonFactory.makeRaichu());
        }

//        selectedPoke = pokemons.get(r.nextInt(3));
        selectedPoke = pokemons.get(0);

//        if(selectedPoke == null) {
//            System.out.println("Initialize Selected pokemon is null");
//        } else {
//            System.out.println("Initialize Selected pokemon is " + selectedPoke.getName());
//        }
    }

    public Player(String username, String pw, List<Pokemon> pokemons) {
        this.username = username;
        this.pw = pw;
        this.pokemons = pokemons;

//        selectedPoke = pokemons.get(r.nextInt(3));
        if (pokemons != null) {
            selectedPoke = pokemons.get(0);
        }

//        if(selectedPoke == null) {
//            System.out.println("Initialize Selected pokemon is null");
//        } else {
//            System.out.println("Initialize Selected pokemon is " + selectedPoke.getName());
//        }
    }

    public SelectedPokeInfo makeSelectedPokeInfo(boolean host) {
        if (selectedPoke == null) {
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
        System.out.println("Pokemon2 list size: " + pokemons.size());
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;

        if (pokemons != null) {
            selectedPoke = pokemons.get(0);
        }
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
