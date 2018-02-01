package com.moekosu.testspringboot;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.Test;
import com.moekosu.constant.Goods;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenxu
 * @date 2018/01
 */
public class GuavaTest {

    @Test
    public void OptionalTest()
    {
        Optional<Goods> good = getOptional();

        // 获取内容，内容必须存在
        good.get();
        // 获取内容，如果是absent，则返回指定的内容(这里传的new Goods())
        good.or(new Goods());
        // 获取内容，如果是absent，返回null
        good.orNull();

        // 判断是否有内容
        System.out.println(good.isPresent());
    }

    private Optional getOptional()
    {
        Goods goods = new Goods();
        goods.setgId(1);
        goods.setGoodsName("names");
        goods.setGoodsNum("2");
        goods.setGoodsDesc("desc");

        // 空内容
        Optional<Goods> emptyNum = Optional.absent();
        // 有内容的对象
        Optional<Goods> someNum = Optional.of(goods);
        // 有可能为空的对象
        Optional<Goods> maybeNum = Optional.fromNullable(goods);

        return emptyNum;
//        return someNum;
//        return maybeNum;
    }

    /**
     * Joiner拼接
     */
    @Test
    public void JoinerTest()
    {
        /**
         on:制定拼接符号，如：test1-test2-test3 中的 “-“ 符号
         skipNulls()：忽略NULL,返回一个新的Joiner实例
         useForNull("none")：NULL的地方都用字符串”Hello”来代替
         withKeyValueSeparator("=")：键值对中的key, value使用=拼接
         ps: 一般使用join或appendTo方法做最后的拼接，分别返回String和StringBuilder
        */
        StringBuilder sb=new StringBuilder();
        StringBuilder zz = Joiner.on(",").skipNulls().appendTo(sb,"Hello","guava", "zz", "long?");
        System.out.println(sb);
        System.out.println(zz);// 返回的是与sb一样的内容

        System.out.println(Joiner.on(",").useForNull("none").join(1,null,3));

        System.out.println(Joiner.on(",").skipNulls().join(Arrays.asList(1,2,3,4,null,6)));

        Map<String,String> map=new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        System.out.println(Joiner.on(",").withKeyValueSeparator("=").join(map));
    }

    /**
     * Splitter分割字符串
     */
    @Test
    public void SplitterTest()
    {
        /**
         on():指定分隔符来分割字符串
         limit():当分割的子字符串达到了limit个时则停止分割
         fixedLength():根据长度来拆分字符串
         trimResults():去掉子串中的空格
         omitEmptyStrings():去掉空的子串
         withKeyValueSeparator():要分割的字符串中key和value间的分隔符,分割后的子串中key和value间的分隔符默认是=
        */
        System.out.println(Splitter.on(",").limit(3).trimResults().split(" a,  b,  c,  d"));//[ a, b, c,d]
        System.out.println(Splitter.fixedLength(3).split("1 2 3"));//[1 2,  3]
        System.out.println(Splitter.on(" ").omitEmptyStrings().splitToList("1  2 3"));
        System.out.println(Splitter.on(",").omitEmptyStrings().split("1,,,,2,,,3"));//[1, 2, 3]
        System.out.println(Splitter.on(" ").trimResults().split("1 2 3")); //[1, 2, 3],默认的连接符是,
        System.out.println(Splitter.on(";").withKeyValueSeparator(":").split("a:1;b:2;c:3"));//{a=1, b=2, c=3}
    }

    /**
     * EventBus订阅测试，同时测试内部类机制
     * 内部类：1.成员内部类：类中类；2.局部内部类：类中方法中类(或作用域)
     */
    @Test
    public void EventBusTest()
    {
        /**
         * 内部类1：事件类
         */
        class OrderEvent
        {
            private String msg;
            public OrderEvent(String msg)
            {
                this.msg = msg;
            }
            String getMsg()
            {
                return msg;
            }
        }
        /**
         * 内部类2：订阅事件类，@Subscribe订阅，可注释在多个方法上，Guava会判断使用哪个方法
         */
        class EventListener1
        {
            // @Subscribe 修饰的方法只能带一个输入参数
            @Subscribe
            public void listen(OrderEvent event)
            {
                System.out.println("EventListener.listen event");
                System.out.println("receive message: "+ event.getMsg());
            }
            @Subscribe
            public void listen(String msg)
            {
                System.out.println("EventListener.listen msg");
                System.out.println("receive message: " + msg);
            }
        }
        /**
         * 内部类3：订阅类2，做多个订阅测试
         */
        class EventListener2
        {
            @Subscribe
            public void listen(OrderEvent event)
            {
                System.out.println("EventListener2.listen event");
                System.out.println("receive message: "+ event.getMsg());
            }
            @Subscribe
            public void listen(String msg)
            {
                System.out.println("EventListener2.listen msg");
                System.out.println("receive message: " + msg);
            }
        }

        /**
         *
         */
        EventBus eventBus = new EventBus("moekosu");
        // 注册订阅者
        eventBus.register(new EventListener1());
        eventBus.register(new EventListener2());
        // 发布事件
        eventBus.post(new OrderEvent("order1"));
        eventBus.post(new OrderEvent("order2"));
        eventBus.post("!");
    }


}
