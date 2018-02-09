package com.moekosu.design.decorator.realDemo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenxu
 * @date 2018/02
 */
public class JsonLogger extends DecoratorLogger {

    public JsonLogger(Logger logger)
    {
        super(logger);
    }

    @Override
    public void trace(String message)
    {

    }

    @Override
    public void debug(String message)
    {

    }

    @Override
    public void info(String message)
    {
        before();
        logger.info(message);
        after();
    }

    @Override
    public void warn(String message)
    {

    }

    @Override
    public void error(String message)
    {

    }

    public static class JsonLoggerFactory{
        public static JsonLogger getLogger(Class clazz)
        {
            Logger logger = LoggerFactory.getLogger(clazz);
            return new JsonLogger(logger);
        }
    }

    private void before()
    {

    }

    private void after()
    {

    }

}
