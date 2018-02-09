package com.moekosu.design.decorator.realDemo1;

import org.slf4j.Logger;
import org.slf4j.Marker;

/**
 * @author chenxu
 * @date 2018/02
 */
public class DecoratorLogger implements Logger {

    private boolean isTrace;
    private boolean isDebug;
    private boolean isInfo;
    private boolean isWarn;
    private boolean isError;

    protected Logger logger;

    public DecoratorLogger(Logger logger1)
    {
        isTrace = true;
        isDebug = true;
        isInfo = true;
        isWarn = true;
        isError = true;

        this.logger = logger1;
    }

    static{

    }

    @Override
    public String getName()
    {
        return ROOT_LOGGER_NAME;
    }

    @Override
    public boolean isTraceEnabled()
    {
        return isTrace;
    }
    @Override
    public void trace(String var1)
    {

    }
    @Override
    public void trace(String var1, Object var2)
    {

    }
    @Override
    public void trace(String var1, Object var2, Object var3)
    {

    }
    @Override
    public void trace(String var1, Object... var2)
    {

    }
    @Override
    public void trace(String var1, Throwable var2)
    {

    }
    @Override
    public boolean isTraceEnabled(Marker var1)
    {
        return isTrace;
    }
    @Override
    public void trace(Marker var1, String var2)
    {

    }
    @Override
    public void trace(Marker var1, String var2, Object var3)
    {

    }
    @Override
    public void trace(Marker var1, String var2, Object var3, Object var4)
    {

    }
    @Override
    public void trace(Marker var1, String var2, Object... var3)
    {

    }
    @Override
    public void trace(Marker var1, String var2, Throwable var3)
    {

    }
    @Override
    public boolean isDebugEnabled()
    {
        return isDebug;
    }
    @Override
    public void debug(String var1)
    {

    }
    @Override
    public void debug(String var1, Object var2)
    {

    }
    @Override
    public void debug(String var1, Object var2, Object var3)
    {

    }
    @Override
    public void debug(String var1, Object... var2)
    {

    }
    @Override
    public void debug(String var1, Throwable var2)
    {

    }
    @Override
    public boolean isDebugEnabled(Marker var1)
    {
        return isDebug;
    }
    @Override
    public void debug(Marker var1, String var2)
    {

    }
    @Override
    public void debug(Marker var1, String var2, Object var3)
    {

    }
    @Override
    public void debug(Marker var1, String var2, Object var3, Object var4)
    {

    }
    @Override
    public void debug(Marker var1, String var2, Object... var3)
    {

    }
    @Override
    public void debug(Marker var1, String var2, Throwable var3)
    {

    }
    @Override
    public boolean isInfoEnabled()
    {
        return isInfo;
    }
    @Override
    public void info(String var1)
    {

    }
    @Override
    public void info(String var1, Object var2)
    {

    }
    @Override
    public void info(String var1, Object var2, Object var3)
    {

    }
    @Override
    public void info(String var1, Object... var2)
    {

    }
    @Override
    public void info(String var1, Throwable var2)
    {

    }
    @Override
    public boolean isInfoEnabled(Marker var1)
    {
        return isInfo;
    }
    @Override
    public void info(Marker var1, String var2)
    {

    }
    @Override
    public void info(Marker var1, String var2, Object var3)
    {

    }
    @Override
    public void info(Marker var1, String var2, Object var3, Object var4)
    {

    }
    @Override
    public void info(Marker var1, String var2, Object... var3)
    {

    }
    @Override
    public void info(Marker var1, String var2, Throwable var3)
    {

    }
    @Override
    public boolean isWarnEnabled()
    {
        return isWarn;
    }
    @Override
    public void warn(String var1)
    {

    }
    @Override
    public void warn(String var1, Object var2)
    {

    }
    @Override
    public void warn(String var1, Object... var2)
    {

    }
    @Override
    public void warn(String var1, Object var2, Object var3)
    {

    }
    @Override
    public void warn(String var1, Throwable var2)
    {

    }
    @Override
    public boolean isWarnEnabled(Marker var1)
    {
        return isWarn;
    }
    @Override
    public void warn(Marker var1, String var2)
    {

    }
    @Override
    public void warn(Marker var1, String var2, Object var3)
    {

    }
    @Override
    public void warn(Marker var1, String var2, Object var3, Object var4)
    {

    }
    @Override
    public void warn(Marker var1, String var2, Object... var3)
    {

    }
    @Override
    public void warn(Marker var1, String var2, Throwable var3)
    {

    }
    @Override
    public boolean isErrorEnabled()
    {
        return isError;
    }
    @Override
    public void error(String var1)
    {

    }
    @Override
    public void error(String var1, Object var2)
    {

    }
    @Override
    public void error(String var1, Object var2, Object var3)
    {

    }
    @Override
    public void error(String var1, Object... var2)
    {

    }
    @Override
    public void error(String var1, Throwable var2)
    {

    }
    @Override
    public boolean isErrorEnabled(Marker var1)
    {
        return isError;
    }
    @Override
    public void error(Marker var1, String var2)
    {

    }
    @Override
    public void error(Marker var1, String var2, Object var3)
    {

    }
    @Override
    public void error(Marker var1, String var2, Object var3, Object var4)
    {

    }
    @Override
    public void error(Marker var1, String var2, Object... var3)
    {

    }
    @Override
    public void error(Marker var1, String var2, Throwable var3)
    {

    }
}
