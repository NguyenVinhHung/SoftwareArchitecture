package model.pokemon;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: s3342128
 * Date: 5/05/13
 * Time: 11:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class PokeMoveRequest extends InBattleRequest implements Serializable {

    private static final long serialVersionUID = 666L;

    private int pokeIndex;
    private int team;
    private int fromI;
    private int fromJ;
    private int toI;
    private int toJ;

    private PokeInBattleInfo[] pokeModels1;
    private PokeInBattleInfo[] pokeModels2;

    public PokeMoveRequest(int pokeIndex, int team, int fromI, int fromJ, int toI, int toJ,
                           PokeInBattleInfo[] pokeModels1, PokeInBattleInfo[] pokeModels2) {
        this.pokeIndex = pokeIndex;
        this.team = team;
        this.fromI = fromI;
        this.fromJ = fromJ;
        this.toI = toI;
        this.toJ = toJ;
        this.pokeModels1 = pokeModels1;
        this.pokeModels2 = pokeModels2;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getPokeIndex() {
        return pokeIndex;
    }

    public void setPokeIndex(int pokeIndex) {
        this.pokeIndex = pokeIndex;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getFromI() {
        return fromI;
    }

    public void setFromI(int fromI) {
        this.fromI = fromI;
    }

    public int getFromJ() {
        return fromJ;
    }

    public void setFromJ(int fromJ) {
        this.fromJ = fromJ;
    }

    public int getToI() {
        return toI;
    }

    public void setToI(int toI) {
        this.toI = toI;
    }

    public int getToJ() {
        return toJ;
    }

    public void setToJ(int toJ) {
        this.toJ = toJ;
    }

    public PokeInBattleInfo[] getPokeModels1() {
        return pokeModels1;
    }

    public void setPokeModels1(PokeInBattleInfo[] pokeModels1) {
        this.pokeModels1 = pokeModels1;
    }

    public PokeInBattleInfo[] getPokeModels2() {
        return pokeModels2;
    }

    public void setPokeModels2(PokeInBattleInfo[] pokeModels2) {
        this.pokeModels2 = pokeModels2;
    }
}
