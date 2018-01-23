package designTest;

import com.moekosu.design.builder.Builder;
import com.moekosu.design.builder.Sender;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * 设计模式：Builder建造者模式
 * 构建多个对象的list
 * @author chenxu
 * @date 2018/01
 */
public class BuilderTest {

    @Test
    public void buildTest()
    {
        Builder builder = new Builder();
        builder.produceMailSender(10);
        List<Sender> list = builder.getBuilderList();
        assertEquals(10, list.size());// 期望的结果, 给予的变量
    }

}
