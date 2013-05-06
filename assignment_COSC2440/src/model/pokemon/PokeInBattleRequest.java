package model.pokemon;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: s3342128
 * Date: 5/05/13
 * Time: 2:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class PokeInBattleRequest extends InBattleRequest implements Serializable {

    private static final long serialVersionUID = 555L;

    private int pokeIndex;
    private int team;
    private int currI;
    private int currJ;
    private int selectedX;
    private int selectedY;
    private int enemyIndex;

    private PokeInBattleInfo[] pokeModels1;
    private PokeInBattleInfo[] pokeModels2;

    private String enemyName;

    public PokeInBattleRequest(int pokeIndex, int team, int selectedX, int selectedY, int enemyIndex) {
        this.pokeIndex = pokeIndex;
        this.team = team;
        this.selectedX = selectedX;
        this.selectedY = selectedY;
        this.enemyIndex = enemyIndex;
    }

    public PokeInBattleRequest(int enemyIndex, PokeInBattleInfo[] pokeModels1, PokeInBattleInfo[] pokeModels2) {
        this.enemyIndex = enemyIndex;
        this.pokeModels1 = pokeModels1;
        this.pokeModels2 = pokeModels2;
    }

    public PokeInBattleRequest(String enemyName, int attackerTeam) {
        this.enemyName = enemyName;
        this.team = attackerTeam;
    }

    public int getCurrI() {
        return currI;
    }

    public void setCurrI(int currI) {
        this.currI = currI;
    }

    public int getCurrJ() {
        return currJ;
    }

    public void setCurrJ(int currJ) {
        this.currJ = currJ;
    }

    public int getPokeIndex() {

        return pokeIndex;
    }

    public int getTeam() {
        return team;
    }

    public int getSelectedX() {
        return selectedX;
    }

    public int getSelectedY() {
        return selectedY;
    }

    public int getEnemyIndex() {
        return enemyIndex;
    }

    public PokeInBattleInfo[] getPokeModels1() {
        return pokeModels1;
    }

    public PokeInBattleInfo[] getPokeModels2() {
        return pokeModels2;
    }

    public void setPokeModels1(PokeInBattleInfo[] pokeModels1) {
        this.pokeModels1 = pokeModels1;
    }

    public void setPokeModels2(PokeInBattleInfo[] pokeModels2) {
        this.pokeModels2 = pokeModels2;
    }

    public void setPokeIndex(int pokeIndex) {
        this.pokeIndex = pokeIndex;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public String getEnemyName() {
        return enemyName;
    }

    @Override
    public String toString() {
        return "PokeInBattleRequest{" +
                "enemyName='" + enemyName + '\'' +
                ", attackerTeam=" + team +
                '}';
    }
}
