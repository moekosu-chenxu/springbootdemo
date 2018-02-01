package designTest;

import com.moekosu.design.bridge.*;
import com.moekosu.design.bridge.sqlDriver.DriverManager;
import com.moekosu.design.bridge.sqlDriver.MyConnection;
import com.moekosu.design.bridge.sqlDriver.SqlInfo;
import org.junit.Test;

/**
 * 设计模式：桥接模式
 * @author chenxu
 * @date 2018/02
 */
public class BridgeTest {

    private static final String JDBC_URL = "jdbc:oracle:thin:@liumo:1521:ORCL";
    private static final String JDBC_USER = "moekosu";
    private static final String JDBC_PASS = "123";

    @Test
    public void bridgeTest()
    {
        Bridge bridge = new MyBridge();

        //
        Sourceable source1 = new SourceSub1();
        bridge.setSource(source1);
        bridge.method();

        //
        Sourceable source2 = new SourceSub2();
        bridge.setSource(source2);
        bridge.method();
    }

    /**
     * 桥接模式，模拟旧式JDBC连接实现方式
     * 先Class.forName()初始化驱动类，这时会调用驱动类中的静态初始块，注册实例进DriverManager中拿到对应的Driver实例
     * 然后调用getConnection()方法，他会使用已经有的Driver实例去调用connect()方法获取MyConnection对象
     */
    @Test
    public void jdbcDriverTest()
    {
        // 初始化类进jvm
        try{
            Class.forName(DriverManager.OWNER_DRIVER);
            // 相当于 new MysqlDriver();
        }
        catch( ClassNotFoundException e )
        {
            System.out.println("new owner driver into jvm fail.");
        }

        MyConnection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        // conn.xxx 后面就是处理sql
    }

}
