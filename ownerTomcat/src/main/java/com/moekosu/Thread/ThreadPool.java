package com.moekosu.Thread;

import com.moekosu.config.ServerConfig;
import com.moekosu.logger.ServerLogger;
import com.moekosu.logger.ServerLoggerFactory;

import javax.net.ServerSocketFactory;
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

    // 线程池容量=最大线程数
    private int max = 10;

    // 当前线程数
    private int current = 0;

    // 排队队列最大数
    private int queueMaxLength = 1000;

    // 排队队列
    private Queue<ProxyRunnable> queue;

    public ThreadPool(int max, int queueMaxLength){
        this.max = max;
        this.queueMaxLength = queueMaxLength;

        queue = new LinkedList<>();
    }

    /**
     * 执行线程
     */
    public boolean execute(int port)
    {
        // 新建一个线程
        ProxyRunnable p = null;
        try{
            ServerSocket serverSocket = ServerSocketFactory.getDefault().createServerSocket(port);
            Socket socket = serverSocket.accept();
            p = new ProxyRunnable(this, socket);
        }
        catch (IOException e){
            logger.error("new proxy fail", e);
        }
        // 当前线程池未满
        if(verify())
        {
            // 执行线程
            new Thread(p).start();
            return true;
        }
        else {
            // 线程池已满，如果排队队列未满，塞入排队队列
            if(queue.size() < queueMaxLength){
                queue.add(p);
                return true;
            }
            else {
                return false;
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
        ThreadPool pool = new ThreadPool(10, 1000);
//        Runnable r1 = new Runnable() {
//            public int port = 8780;
//            @Override
//            public void run() {
//            }
//            public int getPort() {
//                return port;
//            }
//        };
//        Runnable r2 = new Runnable() {
//            public int port = 8781;
//            @Override
//            public void run() {
//            }
//        };
        pool.execute(8780);
//        pool.execute(8781);
    }

}
