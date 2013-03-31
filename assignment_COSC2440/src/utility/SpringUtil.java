package utility;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/30/13
 * Time: 6:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpringUtil {

    public static final ApplicationContext serverCtx = new ClassPathXmlApplicationContext("server_beans.xml");
    public static final ApplicationContext clientCtx = new ClassPathXmlApplicationContext("client_beans.xml");

    public static Object getClientBean(String name) {
        return clientCtx.getBean(name);
    }

    public static Object getServerBean(String name) {
        return serverCtx.getBean(name);
    }
}
