package com.moekosu.design.decorator;

/**
 * 装饰[目标实现类:Source]，能够在前后附加逻辑
 * @author chenxu
 * @date 2018/01
 */
public class Decorator implements Sourceable {

    private Source source;

    public Decorator(Source source)
    {
        this.source = source;
    }

    @Override
    public void method()
    {
        System.out.println("before Decorator");
        source.method();
        System.out.println("after Decorator");
    }

}
