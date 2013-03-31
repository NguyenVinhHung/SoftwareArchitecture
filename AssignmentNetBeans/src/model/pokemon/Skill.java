/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pokemon;

/**
 *
 * @author HungHandsome
 */
public class Skill {
    
    private String name;
    private int type;
    private int damage;
    private int accuracy;
    private int manaUse;

    public Skill(String name, int type, int damage, int accuracy, int manaUse) {
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.accuracy = accuracy;
        this.manaUse = manaUse;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

    public int getAccuracy() {
        return accuracy;
    }
    
}
