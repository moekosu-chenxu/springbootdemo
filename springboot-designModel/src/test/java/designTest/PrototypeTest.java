package designTest;

import com.moekosu.design.prototype.Prototype;
import org.assertj.core.util.DateUtil;
import org.junit.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试原型模式对象流形式的深复制效率
 * @结果 进行100次深复制耗时24毫秒
 * @author chenxu
 * @date 2018/01
 */
public class PrototypeTest {

    @Test
    public void cloneTest() throws IOException, ClassNotFoundException
    {
        Prototype p = new Prototype();
        List<Prototype> list = new ArrayList<>();

        System.out.println("start clone: " + dateFormat());
        for(int i= 0; i< 100; i++)
        {
            list.add((Prototype) p.deepClone());
        }
        System.out.println("clone success: " + list.size());
        System.out.println("end clone: " + dateFormat());
    }

    private String dateFormat()
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curTime = df.format(new Date());

        curTime += " / " + System.currentTimeMillis();

        return curTime;
    }

}
