package com.moekosu.design.proxy;

/**
 * @author chenxu
 * @date 2018/01
 */
public class Source implements Sourceable {

    @Override
    public void method()
    {
        System.out.println("Source.method");
    }

}
