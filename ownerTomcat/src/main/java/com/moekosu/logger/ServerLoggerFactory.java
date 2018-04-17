package com.moekosu.logger;

/**
 * @author chenxu
 * @date 2018/04
 */
public class ServerLoggerFactory {

    private static ServerLogger logger = null;

    private ServerLoggerFactory()
    {
    }

    public static ServerLogger getInstance()
    {
        if(logger == null){
            init();
        }
        return logger;
    }

    private static synchronized void init()
    {
        logger = new ServerLogger();
    }

    /**
     * 防止序列化出错
     * @return
     */
    public Object readResolve()
    {
        return logger;
    }

}
