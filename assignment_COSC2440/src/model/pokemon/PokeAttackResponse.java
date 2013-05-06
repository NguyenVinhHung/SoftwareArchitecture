package model.pokemon;

import java.io.Serializable;

public class PokeAttackResponse implements Serializable {

    private static final long serialVersionUID = 777L;

    private PokeInBattleInfo[] pokeModels1;
    private PokeInBattleInfo[] pokeModels2;

    private String enemyName;
    private String attackerName;

    public PokeAttackResponse(PokeInBattleInfo[] pokeModels1, PokeInBattleInfo[] pokeModels2,
                              String attackerName, String enemyName) {
        this.pokeModels1 = pokeModels1;
        this.pokeModels2 = pokeModels2;
        this.enemyName = enemyName;
        this.attackerName = attackerName;
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

    public String getEnemyName() {
        return enemyName;
    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }

    public String getAttackerName() {
        return attackerName;
    }

    public void setAttackerName(String attackerName) {
        this.attackerName = attackerName;
    }
}
