package com.moekosu.design.adapter.objectadapter;

/**
 * 实现适配器中心的新方法
 * @author chenxu
 * @date 2018/01
 */
public class ObjectAdapter implements Targetable {

    private Source source;

    public ObjectAdapter(Source source)
    {
        super();
        this.source = source;
    }

    @Override
    public void method1()
    {
        source.method1();
    }

    @Override
    public void method2()
    {
        System.out.println("ObjectAdapter.method2");
    }

}
