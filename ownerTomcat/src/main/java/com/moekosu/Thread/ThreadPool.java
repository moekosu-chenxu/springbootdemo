package com.moekosu.Thread;

import com.moekosu.logger.ServerLogger;
import com.moekosu.logger.ServerLoggerFactory;
import com.moekosu.tabs.TabsFilter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author chenxu
 * @date 2018/04
 */
public class ThreadPool {

    private static final ServerLogger logger = ServerLoggerFactory.getInstance();

    private ServerSocket serverSocket;

    // 线程池容量=最大线程数
    private int max = 10;

    // 当前线程数
    private int current = 0;

    // 排队队列最大数
    private int queueMaxLength = 1000;

    // 排队队列
    private Queue<ProxyRunnable> queue;

    public ThreadPool(int max, int queueMaxLength, int port){
        this.max = max;
        this.queueMaxLength = queueMaxLength;

        try {
            this.serverSocket = new ServerSocket(port);
        }
        catch (IOException e) {
            logger.error("init server socket error", e);
        }

        queue = new LinkedList<>();

        // 初始化注解
        System.out.println("开始初始化注解库[TestPath]");
        try {
            TabsFilter.init("com.moekosu.controller");
            System.out.println("初始化注解库[TestPath]成功");
        }
        catch (Exception e) {
            System.out.println("初始化注解库[TestPath]失败："+ e);
        }
    }

    /**
     * 执行线程
     */
    public boolean execute()
    {
        while (true) {
            // 新建一个线程
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                new Thread(new ProxyRunnable(this, socket)).start();
            }
            catch (IOException e) {
                logger.error("server socket accept error", e);
            }
        }
    }

    /**
     * 执行线程前的必要校验，增加线程池被占用数，校验当前线程是否达到最大线程数
     */
    private synchronized boolean verify()
    {
        if(max > current) {
            current++;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 已执行完一个线程
     */
    protected synchronized void minus()
    {
        // 当前线程池空出位置
        current--;
        // 如果排队队列中有人在排队
        if(queue.size() > 0){
            if(verify()){
                // 移除并返回队列中的头部元素
                ProxyRunnable pr = queue.poll();
                // 执行线程
                new Thread(pr).start();
            }
        }
    }

    public int getMax()
    {
        return max;
    }

    public int getCurrent()
    {
        return current;
    }

    public int getQueueMaxLength()
    {
        return queueMaxLength;
    }

    public int getQueueSize()
    {
        return queue.size();
    }

    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(10, 1000, 8780);
        pool.execute();
    }

}
