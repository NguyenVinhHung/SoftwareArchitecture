/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pokemon;

/**
 *
 * @author HungHandsome
 */
public class TypeUtils {
    
    public static final int FIRE = 0;
    public static final int WATER = 1;
    public static final int GRASS = 2;
    public static final int ELECTRICITY = 3;
    public static final int ROCK = 4;
    public static final int BUG = 5;
    public static final int FLYING = 6;
    public static final int NORMAL = 7;
    
    private static final int[][] strongAgainst = {
        {WATER, ROCK}, // Fire
        {GRASS, ELECTRICITY}, // Water
        {FIRE, BUG, FLYING}, // Grass
        {GRASS, ROCK}, // Electricity
        {WATER}, // Rock
        {FIRE, FLYING}, // Bug
        {ELECTRICITY}, // Flying
        {}  // Normal
    };
    
    private static final int[][] weakAgainst = {
        {GRASS, BUG}, // Fire
        {FIRE, ROCK}, // Water
        {WATER, ELECTRICITY}, // Grass
        {WATER, FLYING}, // Electricity
        {FIRE, ELECTRICITY}, // Rock
        {GRASS}, // Bug
        {GRASS, BUG}, // Flying
        {}  // Normal
    };
    
    public static int calculateDamage(Skill skill, Pokemon user, Pokemon receiver) {
        int dmg = skill.getDamage();
        int sklType = skill.getType();
        
        // Increase dmg if the user has the same type as the attack
        if(hasType(user, sklType)) {
            dmg *= 1.5;
        }
        
        dmg *= getDamageChangeRate(sklType, receiver);
        
        return dmg;
    }
    
    public static float getDamageChangeRate(int sklType, Pokemon receiver) {
        int counter = 0;
        
        for(int i=0; i<receiver.getTypes().length; i++) {
            for(int j=0; j<weakAgainst[sklType].length; j++) {
                if(receiver.getType(i) == weakAgainst[sklType][j]) {
                    counter++;
                } else if(j<strongAgainst[sklType].length && receiver.getType(i)==strongAgainst[sklType][j]) {
                    counter--;
                }
            }
        }
        
        if(counter > 0) {
            return 2;
        } else if(counter == 0) {
            return 1;
        } else {
            return 0.5f;
        }
    }
    
    public static boolean hasType(Pokemon pokemon, int type) {
        return pokemon.getType(0)==type || pokemon.getType(1)==type;
    }
    
    public static boolean isWeakAgainst(Pokemon pokemon, int type) {
        for(int i=0; i<pokemon.getTypes().length; i++) {
            for(int j=0; j<weakAgainst[type].length; j++) {
                if(pokemon.getType(i) == weakAgainst[type][j]) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static boolean isStrongAgainst(Pokemon pokemon, int type) {
        for(int i=0; i<pokemon.getTypes().length; i++) {
            for(int j=0; j<strongAgainst[type].length; j++) {
                if(pokemon.getType(i) == strongAgainst[type][j]) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static boolean isWeakAgainst(int type1, int type2) {
        for (int j = 0; j < weakAgainst[type2].length; j++) {
            if (type1 == weakAgainst[type2][j]) {
                return true;
            }
        }
        
        return false;
    }
    
    public static boolean isStrongAgainst(int type1, int type2) {
        for (int j = 0; j < strongAgainst[type2].length; j++) {
            if (type1 == strongAgainst[type2][j]) {
                return true;
            }
        }
        
        return false;
    }
}
