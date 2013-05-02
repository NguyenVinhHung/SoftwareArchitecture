package database;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: CHINHNHAN
 * Date: 4/27/13
 * Time: 3:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_db.xml");

        PlayerDAO playerDAO = (PlayerDAO) context.getBean("playerDao");
        PokemonDAO pokemonDAO = (PokemonDAO) context.getBean("pokemonDao");



//        playerDAO.createPlayerTable();
//        playerDAO.addPlayer("Nhan", "123");
//        playerDAO.addPlayer("Nha", "124");
//        playerDAO.addPlayer("ABC", "124");
//
//        List<Account> accountList = playerDAO.getPlayer("Nhan");
//        System.out.println(accountList.get(0).getPassword());
//
//        System.out.println(playerDAO.authentication("Nha", "124"));

//        pokemonDAO.createPokemonTable();
//
//        pokemonDAO.addPokemon(new Pokemon2("One",1,10,"Grass"),"Nhan");
//        pokemonDAO.addPokemon(new Pokemon2("One2",12,102,"Grass"),"Nha");

        System.out.println(pokemonDAO.getPokemonWithOwner("Nhan").toString());
        System.out.println(pokemonDAO.getPokemonWithOwner("Nha").toString());

    }
}
