package server;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/30/13
 * Time: 7:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Services {

    public static final int REGISTER = 0;
    public static final int LOGIN = 1;
    public static final int ROOM_LIST = 2;
    public static final int CREATE_ROOM = 3;
    public static final int LOGOUT = 10;

    public static final int REGISTER_SUCCESS = 20;
    public static final int REGISTER_FAILED = 21;
    public static final int REGISTER_FAILED_DUPLICATE_NAME = 22;

    public static final int LOGIN_SUCCESS = 30;
    public static final int LOGIN_WRONG_USER = 31;
    public static final int LOGIN_WRONG_PW = 32;
}
