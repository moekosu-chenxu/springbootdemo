package designTest;

import com.moekosu.design.factorymethod.multifactory.MultiFactory;
import com.moekosu.design.factorymethod.normalfactory.SendFactory;
import com.moekosu.design.factorymethod.normalfactory.Sender;
import com.moekosu.design.factorymethod.staticfactory.StaticFactory;
import org.junit.Test;

/**
  * 设计模式：工厂方法模式
  * @author moekosu
  * @description FactoryTest
  * @date 2018/1/10
  */
public class FactoryTest {

    /**
     * 普通工厂方法模式
     * 缺点：必须输入正确的字符串才能取到对应的sender
     */
    @Test
    public void normalFactoryTest()
    {
        SendFactory factory = new SendFactory();
        Sender sender = factory.getInstance("sms");
        sender.send();
    }

    /**
     * 多工厂方法模式
     * 缺点：每出现一个sender就要新增一次factory中的方法
     */
    @Test
    public void multiFactoryTest()
    {
        Sender sender = new MultiFactory().getMailSender();
        sender.send();
    }

    /**
     * 静态工厂方法模式
     * 比较常用，优点：不用实例化工厂类，直接能够调用
     */
    @Test
    public void staticFactoryTest()
    {
        Sender sender = StaticFactory.mailSender();
        sender.send();
    }

}
