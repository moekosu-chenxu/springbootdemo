package com.moekosu.Thread;

import com.moekosu.logger.ServerLogger;
import com.moekosu.logger.ServerLoggerFactory;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    private static final int PORT = 8780;

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

        queue = new LinkedList<ProxyRunnable>();
    }

    /**
     * 执行线程
     * @param runnable 线程对象
     * @return
     */
    public boolean execute(Runnable runnable)
    {
        // 新建一个线程
        ProxyRunnable p = new ProxyRunnable(runnable, this);
        // 当前线程池未满
        if(verify())
        {
            // 执行线程
            new Thread(p).start();

            try{
                ServerSocket serverSocket = ServerSocketFactory.getDefault().createServerSocket(PORT);
                Socket socket = serverSocket.accept();
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();

                // 处理自有request
                Request1 request = new Request1(in);
                request.parse();
                // 处理自有response
                Response1 response = new Response1(out);
                response.setRequest(request);
                response.send();

                socket.close();
            }
            catch (IOException e){
                logger.error("", e);
            }

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
        pool.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

}
