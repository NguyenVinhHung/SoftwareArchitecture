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
    public static final String BUTTERFREE = "Butterfree";
    public static final String BEEDRILL = "Beedrill";
    public static final String PIDGEOT = "Pidgeot";
    public static final String RATICATE = "Raticate";
    public static final String ARBOK = "Arbok";
    public static final String RAICHU = "Raichu";
    public static final String ARTICUNO = "Articuno";
    public static final String ZAPDOS = "Zapdos";
    public static final String MORTRESS = "Mortress";
    public static final String DRAGONITE = "Dragonite";

    public static Pokemon makeCharizard() {
        Skill blastBurn = new Skill("Blast Burn", TypeUtils.FIRE, 80, 100, 5);
        Skill fireBlast = new Skill("Fire Blast", TypeUtils.FIRE, 130, 70, 20);
        Map<String, Skill> skills = new LinkedHashMap<String, Skill>();

        skills.put(blastBurn.getName(), blastBurn);
        skills.put(fireBlast.getName(), fireBlast);

//        return new Pokemon(CHARIZARD, new int[]{TypeUtils.FIRE, TypeUtils.FLYING}, skills,
//                                149, 143, 177, 150, 185, 200);
        return new Pokemon(CHARIZARD, new int[]{TypeUtils.FIRE}, skills,
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

    public static Pokemon makeButterfree() {

        Skill skill1 = SkillFactory.makeDefaultSkill(TypeUtils.FLYING);
        Map<String, Skill> skills = new LinkedHashMap<String, Skill>();

        skills.put(skill1.getName(), skill1);

        return new Pokemon(BUTTERFREE, new int[]{TypeUtils.FLYING}, skills,
                147, 148, 167, 167, 187, 200);
    }

    public static Pokemon makeBeedrill() {

        Skill skill1 = SkillFactory.makeDefaultSkill(TypeUtils.FLYING);
        Map<String, Skill> skills = new LinkedHashMap<String, Skill>();

        skills.put(skill1.getName(), skill1);

        return new Pokemon(BEEDRILL, new int[]{TypeUtils.FLYING}, skills,
                147, 148, 167, 167, 187, 200);
    }

    public static Pokemon makePidgeot() {

        Skill skill1 = SkillFactory.makeDefaultSkill(TypeUtils.FLYING);
        Map<String, Skill> skills = new LinkedHashMap<String, Skill>();

        skills.put(skill1.getName(), skill1);

        return new Pokemon(PIDGEOT, new int[]{TypeUtils.FLYING}, skills,
                147, 148, 167, 167, 187, 200);
    }

    public static Pokemon makeRaticate() {

        Skill skill1 = SkillFactory.makeDefaultSkill(TypeUtils.FLYING);
        Map<String, Skill> skills = new LinkedHashMap<String, Skill>();

        skills.put(skill1.getName(), skill1);

        return new Pokemon(RATICATE, new int[]{TypeUtils.FLYING}, skills,
                147, 148, 167, 167, 187, 200);
    }

    public static Pokemon makeArbok() {

        Skill skill1 = SkillFactory.makeDefaultSkill(TypeUtils.NORMAL);
        Map<String, Skill> skills = new LinkedHashMap<String, Skill>();

        skills.put(skill1.getName(), skill1);

        return new Pokemon(ARBOK, new int[]{TypeUtils.NORMAL}, skills,
                147, 148, 167, 167, 187, 200);
    }

    public static Pokemon makeRaichu() {

        Skill skill1 = SkillFactory.makeDefaultSkill(TypeUtils.ELECTRICITY);
        Map<String, Skill> skills = new LinkedHashMap<String, Skill>();

        skills.put(skill1.getName(), skill1);

        return new Pokemon(RAICHU, new int[]{TypeUtils.ELECTRICITY}, skills,
                147, 148, 167, 167, 187, 200);
    }

    public static Pokemon makeArticuno() {

        Skill skill1 = SkillFactory.makeDefaultSkill(TypeUtils.WATER);
        Map<String, Skill> skills = new LinkedHashMap<String, Skill>();

        skills.put(skill1.getName(), skill1);

        return new Pokemon(ARTICUNO, new int[]{TypeUtils.WATER}, skills,
                147, 148, 167, 167, 187, 200);
    }

    public static Pokemon makeZapdos() {

        Skill skill1 = SkillFactory.makeDefaultSkill(TypeUtils.ELECTRICITY);
        Map<String, Skill> skills = new LinkedHashMap<String, Skill>();

        skills.put(skill1.getName(), skill1);

        return new Pokemon(ZAPDOS, new int[]{TypeUtils.ELECTRICITY}, skills,
                147, 148, 167, 167, 187, 200);
    }

    public static Pokemon makeMortress() {

        Skill skill1 = SkillFactory.makeDefaultSkill(TypeUtils.FIRE);
        Map<String, Skill> skills = new LinkedHashMap<String, Skill>();

        skills.put(skill1.getName(), skill1);

        return new Pokemon(MORTRESS, new int[]{TypeUtils.FIRE}, skills,
                147, 148, 167, 167, 187, 200);
    }

    public static Pokemon makeDragonite() {

        Skill skill1 = SkillFactory.makeDefaultSkill(TypeUtils.FLYING);
        Map<String, Skill> skills = new LinkedHashMap<String, Skill>();

        skills.put(skill1.getName(), skill1);

        return new Pokemon(DRAGONITE, new int[]{TypeUtils.FLYING}, skills,
                147, 148, 167, 167, 187, 200);
    }

    public static Image getFrontGifImage(String pokeName) {
        System.out.println("Update image");
        if (pokeName.equals(CHARIZARD)) {
            return FileUtility.CHARIZARD_FRONT_IMG;
        } else if (pokeName.equals(BLASTOISE)) {
            return FileUtility.BLASTOISE_FRONT_IMG;
        } else if (pokeName.equals(VENUSAUR)) {
            return FileUtility.VENUSAUR_FRONT_IMG;
        } else if (pokeName.equals(BUTTERFREE)) {
            return FileUtility.BUTTERFREE_FRONT_IMG;
        } else if (pokeName.equals(BEEDRILL)) {
            return FileUtility.BEEDRILL_FRONT_IMG;
        } else if (pokeName.equals(PIDGEOT)) {
            return FileUtility.PIDGEOT_FRONT_IMG;
        } else if (pokeName.equals(RATICATE)) {
            return FileUtility.RATICATE_FRONT_IMG;
        } else if (pokeName.equals(ARBOK)) {
            return FileUtility.ARBOK_FRONT_IMG;
        } else if (pokeName.equals(RAICHU)) {
            return FileUtility.RAICHU_FRONT_IMG;
        } else if (pokeName.equals(ARTICUNO)) {
            return FileUtility.ARTICUNO_FRONT_IMG;
        } else if (pokeName.equals(ZAPDOS)) {
            return FileUtility.ZAPDOS_FRONT_IMG;
        } else if (pokeName.equals(MORTRESS)) {
            return FileUtility.MORTRESS_FRONT_IMG;
        } else if (pokeName.equals(DRAGONITE)) {
            return FileUtility.DRAGONITE_FRONT_IMG;
        }
        return null;
    }

    public static Image getBackGifImage(String pokeName) {
        System.out.println("Update image");
        if (pokeName.equals(CHARIZARD)) {
            return FileUtility.CHARIZARD_BACK_IMG;
        } else if (pokeName.equals(BLASTOISE)) {
            return FileUtility.BLASTOISE_BACK_IMG;
        } else if (pokeName.equals(VENUSAUR)) {
            return FileUtility.VENUSAUR_BACK_IMG;
        }else if (pokeName.equals(BUTTERFREE)) {
            return FileUtility.BUTTERFREE_BACK_IMG;
        } else if (pokeName.equals(BEEDRILL)) {
            return FileUtility.BEEDRILL_BACK_IMG;
        } else if (pokeName.equals(PIDGEOT)) {
            return FileUtility.PIDGEOT_BACK_IMG;
        } else if (pokeName.equals(RATICATE)) {
            return FileUtility.RATICATE_BACK_IMG;
        } else if (pokeName.equals(ARBOK)) {
            return FileUtility.ARBOK_BACK_IMG;
        } else if (pokeName.equals(RAICHU)) {
            return FileUtility.RAICHU_BACK_IMG;
        } else if (pokeName.equals(ARTICUNO)) {
            return FileUtility.ARTICUNO_BACK_IMG;
        } else if (pokeName.equals(ZAPDOS)) {
            return FileUtility.ZAPDOS_BACK_IMG;
        } else if (pokeName.equals(MORTRESS)) {
            return FileUtility.MORTRESS_BACK_IMG;
        } else if (pokeName.equals(DRAGONITE)) {
            return FileUtility.DRAGONITE_BACK_IMG;
        }
        return null;
    }

    public static Image getPokeAvatar(String pokeName) {
//        System.out.println("Update avatar of SelectedPokeView");
        if (pokeName.equals(CHARIZARD)) {
            return FileUtility.CHARIZARD_AVAR;
        } else if (pokeName.equals(BLASTOISE)) {
            return FileUtility.BLASTOISE_AVAR;
        } else if (pokeName.equals(VENUSAUR)) {
            return FileUtility.VENUSAUR_AVAR;
        }else if (pokeName.equals(BUTTERFREE)) {
            return FileUtility.BUTTERFREE_AVAR;
        } else if (pokeName.equals(BEEDRILL)) {
            return FileUtility.BEEDRILL_AVAR;
        } else if (pokeName.equals(PIDGEOT)) {
            return FileUtility.PIDGEOT_AVAR;
        } else if (pokeName.equals(RATICATE)) {
            return FileUtility.RATICATE_AVAR;
        } else if (pokeName.equals(ARBOK)) {
            return FileUtility.ARBOK_AVAR;
        } else if (pokeName.equals(RAICHU)) {
            return FileUtility.RAICHU_AVAR;
        } else if (pokeName.equals(ARTICUNO)) {
            return FileUtility.ARTICUNO_AVAR;
        } else if (pokeName.equals(ZAPDOS)) {
            return FileUtility.ZAPDOS_AVAR;
        } else if (pokeName.equals(MORTRESS)) {
            return FileUtility.MORTRESS_AVAR;
        } else if (pokeName.equals(DRAGONITE)) {
            return FileUtility.DRAGONITE_AVAR;
        }

        System.out.println("Null avatar");
        return null;
    }

    public static Image getPokeIcon(String pokeName) {
        if (pokeName.equals(CHARIZARD)) {
            return FileUtility.CHARIZARD_ICON;
        } else if (pokeName.equals(BLASTOISE)) {
            return FileUtility.BLASTOISE_ICON;
        } else if (pokeName.equals(VENUSAUR)) {
            return FileUtility.VENUSAUR_ICON;
        }else if (pokeName.equals(BUTTERFREE)) {
            return FileUtility.BUTTERFREE_ICON;
        } else if (pokeName.equals(BEEDRILL)) {
            return FileUtility.BEEDRILL_ICON;
        } else if (pokeName.equals(PIDGEOT)) {
            return FileUtility.PIDGEOT_ICON;
        } else if (pokeName.equals(RATICATE)) {
            return FileUtility.RATICATE_ICON;
        } else if (pokeName.equals(ARBOK)) {
            return FileUtility.ARBOK_ICON;
        } else if (pokeName.equals(RAICHU)) {
            return FileUtility.RAICHU_ICON;
        } else if (pokeName.equals(ARTICUNO)) {
            return FileUtility.ARTICUNO_ICON;
        } else if (pokeName.equals(ZAPDOS)) {
            return FileUtility.ZAPDOS_ICON;
        } else if (pokeName.equals(MORTRESS)) {
            return FileUtility.MORTRESS_ICON;
        } else if (pokeName.equals(DRAGONITE)) {
            return FileUtility.DRAGONITE_ICON;
        }
        return null;
    }

    public static String getPokeIconURL(String pokeName) {
        if (pokeName.equals(CHARIZARD)) {
            return FileUtility.CHARIZARD_ICON_URL;
        } else if (pokeName.equals(BLASTOISE)) {
            return FileUtility.BLASTOISE_ICON_URL;
        } else if (pokeName.equals(VENUSAUR)) {
            return FileUtility.VENUSAUR_ICON_URL;
        }else if (pokeName.equals(BUTTERFREE)) {
            return FileUtility.BUTTERFREE_ICON_URL;
        } else if (pokeName.equals(BEEDRILL)) {
            return FileUtility.BEEDRILL_ICON_URL;
        } else if (pokeName.equals(PIDGEOT)) {
            return FileUtility.PIDGEOT_ICON_URL;
        } else if (pokeName.equals(RATICATE)) {
            return FileUtility.RATICATE_ICON_URL;
        } else if (pokeName.equals(ARBOK)) {
            return FileUtility.ARBOK_ICON_URL;
        } else if (pokeName.equals(RAICHU)) {
            return FileUtility.RAICHU_ICON_URL;
        } else if (pokeName.equals(ARTICUNO)) {
            return FileUtility.ARTICUNO_ICON_URL;
        } else if (pokeName.equals(ZAPDOS)) {
            return FileUtility.ZAPDOS_ICON_URL;
        } else if (pokeName.equals(MORTRESS)) {
            return FileUtility.MORTRESS_ICON_URL;
        } else if (pokeName.equals(DRAGONITE)) {
            return FileUtility.DRAGONITE_ICON_URL;
        }

        System.out.println("pokemon icon: null");
        return "";
    }
}
