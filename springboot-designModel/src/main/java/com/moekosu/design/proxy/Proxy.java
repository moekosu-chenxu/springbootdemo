package com.moekosu.design.proxy;

/**
 * 设计模式：代理模式（静态代理）
 * ps：动态代理涉及到class.forName()反射机制 或 cglib编码库(运用asm字节码处理框架生成类)
 * @author chenxu
 * @date 2018/01
 */
public class Proxy implements Sourceable{

    private Source source;

    public Proxy()
    {
        super();
        this.source = new Source();
    }

    @Override
    public void method()
    {
        before();
        source.method();
        after();
    }

    private void before()
    {
        System.out.println("Proxy.before");
    }

    private void after()
    {
        System.out.println("Proxy.after");
    }

    public static void main(String[] args) {
        Sourceable p = new Proxy();
        p.method();
    }

}
