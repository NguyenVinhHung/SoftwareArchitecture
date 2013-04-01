package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/31/13
 * Time: 8:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClientSpring {

    private static ApplicationContext clientCtx;

    public static Object getBean(String name) {
        if(clientCtx == null) {
            clientCtx = new ClassPathXmlApplicationContext("client_beans.xml");
        }
        return clientCtx.getBean(name);
    }
}
