package model.pokemon;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 4/1/13
 * Time: 8:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class SkillFactory {

    public static Skill makeDefaultSkill(int type) {
        return new Skill("Default attack", type, 80, 100, 5);
    }


}
