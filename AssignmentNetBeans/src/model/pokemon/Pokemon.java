/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pokemon;

import java.util.Map;

/**
 *
 * @author HungHandsome
 */
public class Pokemon {
    
    private String name;
    private Map<String, Skill> skills;
    private int[] types;
    private int atk;
    private int def;
    private int agi;
    private int accuracy;
    private int hp;

    public Pokemon(String name, int[] types, Map<String, Skill> skills,
                    int atk, int def, int agi, int accuracy, int hp) {
        this.name = name;
        this.types = types;
        this.skills = skills;
        this.atk = atk;
        this.def = def;
        this.agi = agi;
        this.accuracy = accuracy;
        this.hp = hp;
        
    }

    public String getName() {
        return name;
    }
    
    public int[] getTypes() {
        return types;
    }
    
    public int getType(int index) {
        return types[index];
    }
    
    public Map<String, Skill> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, Skill> skills) {
        this.skills = skills;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getAgi() {
        return agi;
    }

    public void setAgi(int agi) {
        this.agi = agi;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    
}
