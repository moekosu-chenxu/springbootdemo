package com.moekosu.testspringboot;

import org.junit.Test;

import java.sql.*;

/**
 * 基础JDBC操作，对比验证设计模式：Bridge桥接模式
 * @author chenxu
 * @date 2018/02
 */
public class JDBCTest {

    private static final String driverClass = "com.mysql.jdbc.Driver";
    private static final String jdbcUrl = "jdbc:oracle:thin:@liumo:1521:ORCL";
    private static final String user = "test_lm";
    private static final String password = "test_lm";

    @Test
    public void jdbcTest() throws ClassNotFoundException, SQLException
    {
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        Class.forName(driverClass);

        conn = DriverManager.getConnection(jdbcUrl, user, password);
        stat = conn.createStatement();
        // 执行sql语句
        rs = stat.executeQuery("select * from student");
        // 结果
        while(rs.next()){
            System.out.println(rs.getString("name"));
        }
    }

}
