package com.moekosu.design.bridge.sqlDriver;

/**
 * @author chenxu
 * @date 2018/02
 */
public abstract class DriverManager {

    private static Driver driver;

    private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    private static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String OWNER_DRIVER = "com.moekosu.design.bridge.sqlDriver.MysqlDriver";

    /**
     * 注册driver对象，初始化
     * @param driver1
     */
    public static void registerDriver(Driver driver1)
    {
        driver = driver1;
    }

    public static MyConnection getConnection(String url, String user, String password)
    {
        SqlInfo info = new SqlInfo();
        info.setUrl(url);
        info.setUser(user);
        info.setPassword(password);
        return driver.connect(url, info);
    }

}
