package database;

/**
 * Created with IntelliJ IDEA.
 * User: CHINHNHAN
 * Date: 4/28/13
 * Time: 9:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class Pokemon2 {

    private String pokemonName;
    private int pokemonDamage;
    private int health;
    private int type;

    public Pokemon2(String pokemonName, int pokemonDamage, int health, int type) {
        this.pokemonName = pokemonName;
        this.pokemonDamage = pokemonDamage;
        this.health = health;
        this.type = type;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public int getPokemonDamage() {
        return pokemonDamage;
    }

    public void setPokemonDamage(int pokemonDamage) {
        this.pokemonDamage = pokemonDamage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Pokemon2{" +
                "pokemonName='" + pokemonName + '\'' +
                ", pokemonDamage=" + pokemonDamage +
                ", health=" + health +
                ", type='" + type + '\'' +
                '}';
    }
}
