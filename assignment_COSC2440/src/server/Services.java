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
    public static final int NOTIFY = 4;
    public static final int LOGOUT = 10;

    public static final int REGISTER_SUCCESS = 20;
    public static final int REGISTER_FAILED = 21;
    public static final int REGISTER_FAILED_DUPLICATE_NAME = 22;

    public static final int LOGIN_SUCCESS = 30;
    public static final int LOGIN_WRONG_USER = 31;
    public static final int LOGIN_WRONG_PW = 32;

    public static final int GET_IN_ROOM = 40;
    public static final int GET_IN_ROOM_SUCCESS = 41;
    public static final int GET_IN_ROOM_FAILED = 42;
    public static final int GET_IN_ROOM_T1 = 43;
    public static final int GET_IN_ROOM_T2 = 44;

    public static final int IN_ROOM_GET_SELECTED_POK = 50;
    public static final int IN_ROOM_GET_POK_LIST = 51;

    public static final int CLIENT_SEND_MESS = 60;
}
