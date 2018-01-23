package com.moekosu.design.singleton;

/**
 * @author chenxu
 * @date 2018/01
 */
public class SingletonMultiThread {

    // 私有的封闭的构造方法，防止被实例化
    private SingletonMultiThread(){
    }

    /**
     * 采用内部类的方式
     * JVM内部机制保证类的创建是个线程互斥的过程
     * 所以当第一次调用这个类的时候，就会创建这个内部类同时初始化single
     * ps: 有可能出问题是在，构造方法不能出错，出错了就不会创建这个对象了
     */
    private static class SingletonFactory{
        private static SingletonMultiThread single = new SingletonMultiThread();
    }

    // 正式暴露的获取实例化对象的方法
    public SingletonMultiThread getInstance()
    {
        return SingletonFactory.single;
    }

    // 防止序列化出错
    public Object readResolve()
    {
        return getInstance();
    }

}
