package server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/31/13
 * Time: 8:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServerSpring {

    private static ApplicationContext serverCtx;

    public static Object getBean(String name) {
        if(serverCtx == null) {
            serverCtx = new ClassPathXmlApplicationContext("server_beans.xml");
        }
        return serverCtx.getBean(name);
    }
}
