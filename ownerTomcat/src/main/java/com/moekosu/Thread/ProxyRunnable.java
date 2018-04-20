package com.moekosu.Thread;

import com.moekosu.config.ServerConfig;
import com.moekosu.logger.ServerLogger;
import com.moekosu.logger.ServerLoggerFactory;

import java.io.*;
import java.net.Socket;

/**
 * @author chenxu
 * @date 2018/04
 */
public class ProxyRunnable implements Runnable {

    private static final ServerLogger logger = ServerLoggerFactory.getInstance();

    private Runnable runnable;

    private ThreadPool tp;

    private Socket socket;

    /**
     * 执行线程，同时操作线程池
     */
    @Override
    public void run()
    {
        while (true){
            // 执行线程
//            runnable.run();
            // 启动容器
            socket();
            // 执行完线程腾出空间
            tp.minus();
        }
    }

    private void socket()
    {
        if(socket == null){
            return;
        }
        try{
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            // 处理自有request
            Request1 request = new Request1(in);
            //
            if(request.getUri() == null){
                return;
            }
            // 处理自有response
            Response1 response = new Response1(out);
            response.setHeader("Content-Type", ServerConfig.getTypeConfig("html"));
            // 获取资源文件
            File file = new File(ServerConfig.getWeb_root(), request.getUri());
            if(file.exists()){
                response.setStatus(200);
            }
            else{
                response.setStatus(404);
                file = new File(ServerConfig.getWeb_root(), ServerConfig.getError_page());
            }
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] buff = new byte[(int) file.length()];
            bis.read(buff);
            // 写入response
            response.write(buff);
            bis.close();
        }
        catch (IOException e){
            logger.error("", e);
        }
    }

    /**
     * 初始化线程对象
     */
    public ProxyRunnable(ThreadPool tp, Socket socket)
    {
        this.tp = tp;
        this.socket = socket;
    }

}
