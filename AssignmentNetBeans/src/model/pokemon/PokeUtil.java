package model.pokemon;

import java.util.Calendar;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 4/1/13
 * Time: 8:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class PokeUtil {

    public static final int ATTACK_MISS = -1;

    public static int calculateDamage(Skill skill, Pokemon user, Pokemon receiver) {
        Random accurateChecker = new Random(Calendar.getInstance().getTimeInMillis());
        int accu = accurateChecker.nextInt(user.getAccuracy() + skill.getAccuracy()/2 + receiver.getAgi()) + 1;

        if(accu > user.getAccuracy() + skill.getAccuracy()/2) {
            return ATTACK_MISS;
        }

        int damge = TypeUtils.calculateDamage(skill, user, receiver);
        damge -= receiver.getDef();
        damge = (damge <= 0) ? 1 : damge;

        return damge;
    }
}
