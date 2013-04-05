package model.pokemon;

import utility.FileUtility;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 4/1/13
 * Time: 8:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class PokemonFactory {

    public static final String CHARIZARD = "Charizard";
    public static final String BLASTOISE = "Blastoise";
    public static final String VENUSAUR = "Venusaur";

    public static Pokemon makeCharizard() {
        Skill blastBurn = new Skill("Blast Burn", TypeUtils.FIRE, 80, 100, 5);
        Skill fireBlast = new Skill("Fire Blast", TypeUtils.FIRE, 130, 70, 20);
        Map<String, Skill> skills = new LinkedHashMap<String, Skill>();

        skills.put(blastBurn.getName(), blastBurn);
        skills.put(fireBlast.getName(), fireBlast);

        return new Pokemon(CHARIZARD, new int[]{TypeUtils.FIRE, TypeUtils.FLYING}, skills,
                                149, 143, 177, 150, 185, 200);
    }

    public static Pokemon makeBlastoise() {
        Skill hydroCannon = new Skill("Hydro Cannon", TypeUtils.WATER, 80, 100, 5);
        Skill hydroPump = new Skill("Hydro Pump", TypeUtils.WATER, 130, 70, 20);
        Map<String, Skill> skills = new LinkedHashMap<String, Skill>();

        skills.put(hydroCannon.getName(), hydroCannon);
        skills.put(hydroPump.getName(), hydroPump);

        return new Pokemon(BLASTOISE, new int[]{TypeUtils.WATER}, skills,
                148, 167, 150, 172, 186, 200);
    }

    public static Pokemon makeVenusaur() {
        Skill frenzyPlant = new Skill("Frenzy Plant", TypeUtils.FIRE, 80, 100, 5);
        Skill woodHammer = new Skill("Wood Hammer", TypeUtils.FIRE, 130, 70, 20);
        Map<String, Skill> skills = new LinkedHashMap<String, Skill>();

        skills.put(frenzyPlant.getName(), frenzyPlant);
        skills.put(woodHammer.getName(), woodHammer);

        return new Pokemon(VENUSAUR, new int[]{TypeUtils.GRASS}, skills,
                147, 148, 167, 167, 187, 200);
    }

    public static Image getGifImage(String pokeName) {
        System.out.println("Update image of SelectedPokeView");
        if(pokeName.equals(CHARIZARD)) {
            return FileUtility.CHARIZARD_IMG;
        } else if(pokeName.equals(BLASTOISE)) {
            return FileUtility.BLASTOISE_IMG;
        } else if(pokeName.equals(VENUSAUR)) {
            return FileUtility.VENUSAUR_IMG;
        }
        return null;
    }
}
