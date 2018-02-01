package com.moekosu.design.bridge.sqlDriver;


/**
 * @author chenxu
 * @date 2018/02
 */
public class MysqlDriver implements Driver {

    // 做初始化
    static{
        com.moekosu.design.bridge.sqlDriver.DriverManager.registerDriver(new MysqlDriver());
    }

    @Override
    public MyConnection connect(String url, SqlInfo info)
    {
        MyConnection conn = new MyConnection();

        return conn;
    }

}
