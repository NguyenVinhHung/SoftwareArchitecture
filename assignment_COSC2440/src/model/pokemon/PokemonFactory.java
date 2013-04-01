package model.pokemon;

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

    public static Pokemon makeCharizard() {
        Skill blastBurn = new Skill("Blast Burn", TypeUtils.FIRE, 80, 100, 5);
        Skill fireBlast = new Skill("Fire Blast", TypeUtils.FIRE, 130, 70, 20);
        Map<String, Skill> skills = new LinkedHashMap<String, Skill>();

        skills.put(blastBurn.getName(), blastBurn);
        skills.put(fireBlast.getName(), fireBlast);

        Pokemon p = new Pokemon("Charizard", new int[]{TypeUtils.FIRE, TypeUtils.FLYING}, skills,
                                149, 143, 177, 150, 185, 200);
        return p;
    }

    public static Pokemon makeBlastoise() {
        Skill hydroCannon = new Skill("Hydro Cannon", TypeUtils.WATER, 80, 100, 5);
        Skill hydroPump = new Skill("Hydro Pump", TypeUtils.WATER, 130, 70, 20);
        Map<String, Skill> skills = new LinkedHashMap<String, Skill>();

        skills.put(hydroCannon.getName(), hydroCannon);
        skills.put(hydroPump.getName(), hydroPump);

        Pokemon p = new Pokemon("Blastoise", new int[]{TypeUtils.WATER}, skills,
                148, 167, 150, 172, 186, 200);
        return p;
    }

    public static Pokemon makeVenusaur() {
        Skill frenzyPlant = new Skill("Frenzy Plant", TypeUtils.FIRE, 80, 100, 5);
        Skill woodHammer = new Skill("Wood Hammer", TypeUtils.FIRE, 130, 70, 20);
        Map<String, Skill> skills = new LinkedHashMap<String, Skill>();

        skills.put(frenzyPlant.getName(), frenzyPlant);
        skills.put(woodHammer.getName(), woodHammer);

        Pokemon p = new Pokemon("Venusaur", new int[]{TypeUtils.GRASS}, skills,
                147, 148, 167, 167, 187, 200);
        return p;
    }
}
