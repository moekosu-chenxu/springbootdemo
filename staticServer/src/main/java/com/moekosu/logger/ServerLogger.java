package com.moekosu.logger;

/**
 * @author chenxu
 * @date 2018/04
 */
public class ServerLogger {

    public ServerLogger(){

    }

    public void debug(String msg)
    {
        System.out.println("debug log: " + msg);
    }

    public void error(String msg, Exception e)
    {
        System.out.println("error log: " + msg + "\n"+ e.getMessage());
    }

}
