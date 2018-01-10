package designTest;

import com.moekosu.design.adapter.classadapter.ClassAdapter;
import com.moekosu.design.adapter.classadapter.Targetable;
import com.moekosu.design.adapter.interfaceadapter.SourceSub1;
import com.moekosu.design.adapter.interfaceadapter.SourceSub2;
import com.moekosu.design.adapter.objectadapter.ObjectAdapter;
import com.moekosu.design.adapter.objectadapter.Source;
import org.junit.Test;

/**
 * 设计模式：适配器模式
 * @author chenxu
 * @date 2018/01
 */
public class AdapterTest {

    /**
      * 类适配器：
      * 使用一个[中间接口类:Targetable]，定义目标类方法，同时定义新方法
      * 在[适配器类:ObjectAdapter]继承目标类实现中间接口后，就约等于目标类实现了中间接口中的对应方法
      * 这时我们就能够使用[适配器类]来调用到[目标类:Source]的方法以及新定义方法
      */
    @Test
    public void classAdapterTest()
    {
        ClassAdapter target = new ClassAdapter();
        target.method1();
        target.method2();
    }

    /**
     * 对象适配器：
     * [适配器类:ObjectAdapter]中包含目标类，直接调用目标类的方法来实现适配
     */
    @Test
    public void objectAdapterTest()
    {
        Source source = new Source();
        ObjectAdapter adapter = new ObjectAdapter(source);
        adapter.method1();
        adapter.method2();
    }

    /**
     * 接口适配器：
     * 用一个[抽象类:Wrapper]实现[目标接口:Sourceable]，并实现所有方法
     * 然后我们就可以用[自己的类:SourceSub]去继承抽象类来重写自己需要的方法，而不用实现全部方法
     */
    @Test
    public void interfaceAdapterTest()
    {
        SourceSub1 sub1 = new SourceSub1();
        SourceSub2 sub2 = new SourceSub2();

        sub1.method1();
        sub2.method2();
    }

}
