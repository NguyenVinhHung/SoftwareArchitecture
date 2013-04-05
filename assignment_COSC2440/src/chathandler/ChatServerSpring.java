package chathandler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: s3309665
 * Date: 5/04/13
 * Time: 10:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class ChatServerSpring {
    private static ApplicationContext serverCtx;

    public static Object getBean(String name) {
        if(serverCtx == null) {
            serverCtx = new ClassPathXmlApplicationContext("chat_server_beans.xml");
        }
        return serverCtx.getBean(name);
    }
}
