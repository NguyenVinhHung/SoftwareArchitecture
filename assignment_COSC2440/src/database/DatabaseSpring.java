package database;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 5/1/13
 * Time: 9:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseSpring {

    private static ApplicationContext context;

    public static PlayerDAOImpl getPlayerDAO() {
        if(context == null) {
            context = new ClassPathXmlApplicationContext("spring_db.xml");
        }
        return (PlayerDAOImpl) context.getBean("playerDao");
    }

    public static PokemonDAOImpl getPokemonDAO() {
        if(context == null) {
            context = new ClassPathXmlApplicationContext("spring_db.xml");
        }
        return (PokemonDAOImpl) context.getBean("pokemonDao");
    }
}
