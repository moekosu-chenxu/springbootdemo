package com.moekosu.Thread;

/**
 * @author chenxu
 * @date 2018/04
 */
public class ProxyRunnable implements Runnable {

    private Runnable runnable;

    private ThreadPool tp;

    /**
     * 执行线程，同时操作线程池
     */
    @Override
    public void run()
    {
        // 执行线程
        runnable.run();
        // 执行完线程腾出空间
        tp.minus();
    }

    /**
     * 初始化线程对象
     * @param r  具体执行的线程对象
     * @param tp 线程池对象
     */
    public ProxyRunnable(Runnable r, ThreadPool tp)
    {
        this.runnable = r;
        this.tp = tp;
    }

}
