package designTest;

import com.moekosu.design.abstractfactory.MailSendFactory;
import com.moekosu.design.abstractfactory.Provider;
import com.moekosu.design.abstractfactory.Sender;
import org.junit.Test;

/**
 * 设计模式：抽象工厂模式
 * 优点：比工厂模式拓展性好
 * @author chenxu
 * @date 2018/01
 */
public class AbstractFactoryTest {

    @Test
    public void abstractTest()
    {
        Provider provider = new MailSendFactory();
        Sender sender = provider.produce();
        sender.send();
    }

}
