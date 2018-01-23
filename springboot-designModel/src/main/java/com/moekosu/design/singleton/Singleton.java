package com.moekosu.design.singleton;

/**
 * @author chenxu
 * @date 2018/01
 */
public class Singleton {

    // 设null 延迟加载
    private static Singleton singleton = null;

    // 私有的封闭的构造方法，防止被实例化
    private Singleton(){
    }

    // 正式暴露的获取单个实例化对象的方法
    public static Singleton getInstance()
    {
        if( singleton == null )
        {
            // 1.第一次进来的时候做同步锁住
            // 但是在多线程下可能会有问题，因为 singleton = xx 不是原子操作
//            synchronized (singleton){
//                if(singleton == null){
//                    singleton = new Singleton();
//                }
//            }
            // 2.或者使用外带同步方法的方式来初始化
//            init();
            // 3.建议多线程版本使用SingletonMultiThread
            singleton = new Singleton();
        }
        return singleton;
    }

    private static synchronized void init()
    {
        if(singleton == null){
            singleton = new Singleton();
        }
    }

    // 防止序列化出错
    public Object readResolve()
    {
        return singleton;
    }

}
