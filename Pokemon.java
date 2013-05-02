package model.pokemon;

import java.io.Serializable;
 import java.util.Map;

 /**
 *
 *
 */
public class Pokemon implements Serializable {

    private static final long serialVersionUID = 2222222222222222222L;
    
    private String name;
    private Map<String, Skill> skills;
    private int[] types;
    private int atk;
    private int def;
    private int agi;
    private int accuracy;
    private int hp;
    private int mana;
    private int lv = 1;

    private int x;
    private int y;

    public Pokemon(String name, int[] types, Map<String, Skill> skills,
                    int atk, int def, int agi, int accuracy, int hp, int mana) {
        this.name = name;
        this.types = types;
        this.skills = skills;
        this.atk = atk;
        this.def = def;
        this.agi = agi;
        this.accuracy = accuracy;
        this.hp = hp;
        this.mana = mana;
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

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }
}
