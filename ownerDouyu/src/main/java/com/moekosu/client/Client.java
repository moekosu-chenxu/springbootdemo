package com.moekosu.client;

import com.moekosu.constant.DouyuResp;
import com.moekosu.constant.DouyuRoom;
import com.moekosu.constant.KeepAlive;
import com.moekosu.util.STTUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenxu
 * @date 2018/05
 */
public class Client {

    private String ip;
    private int port;

    private Socket socket;
    private long lastSendTime;
    private boolean running = false;

    public static void main(String[] args) throws Exception {
        Client client = new Client("openbarrage.douyutv.com", 8601);
        client.start("610254");
    }

    public Client(String ip, int port)
    {
        this.ip = ip;
        this.port = port;
    }

    public void start(String roomId) throws Exception
    {
        if (running) {
            return;
        }
        socket = new Socket(ip, port);
        System.out.println("本地端口: "+ socket.getLocalPort());
        //
        lastSendTime = System.currentTimeMillis();
        running = true;
        // 登陆授权
        login(roomId);
        // 接受弹幕
//        new Thread(new receive()).start();
        // 心跳
//        new Thread(new heart()).start();
    }

    public void stop()
    {
        if(running) {
            running = false;
        }
    }

    public void sendObject(Object object) throws IOException
    {
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(object);
        System.out.println("发送: \t"+ object);
        oos.flush();
    }

    public void getResp() throws Exception
    {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Object obj = ois.readObject();
        DouyuResp resp = DouyuResp.parseResp(obj);
        System.out.println(resp.toString());
    }

    public void login(String roomid) throws Exception
    {
        Map<String, String> map = new HashMap<>();
        map.put("type", "loginreq");
        map.put("roomid", roomid);
        this.sendObject(STTUtil.encodeMultiValue(map));
        this.getResp();
    }

    /**
     * 心跳消息
     */
    class heart implements Runnable
    {
        long checkDelay = 10;
        // 心跳定时(毫秒)
        long keepAliveDelay = 2000;

        @Override
        public void run()
        {
            // 当系统在运行时，定时发送心跳
            while (running)
            {
                // 超过了心跳时间，发送心跳消息
                if(System.currentTimeMillis() - lastSendTime > keepAliveDelay) {
                    try {
                        Map<String, String> map = new HashMap<>();
                        map.put("type", "mrkl");
                        Client.this.sendObject(STTUtil.encodeMultiValue(map));
                    }
                    catch (IOException e) {
                        System.out.println("发送心跳消息失败: " + e);
                        Client.this.stop();
                    }
                    lastSendTime = System.currentTimeMillis();
                }
                else {
                    try {
                        Thread.sleep(checkDelay);
                    }
                    catch (Exception e) {
                        System.out.println("线程延迟检查失败: "+ e);
                        Client.this.stop();
                    }
                }
            }
        }
    }

    /**
     * 接受协议消息
     */
    class receive implements Runnable
    {
        @Override
        public void run()
        {
            while (running)
            {
                try {
                    InputStream in = socket.getInputStream();
                    if(in.available() > 0) {
                        ObjectInputStream ois = new ObjectInputStream(in);
                        Object obj = ois.readObject();
                        System.out.println("接收:\t"+ obj);
                        DouyuResp resp = DouyuResp.parseResp(obj);
                        System.out.println(resp.toString());
                    }
                    else {
                        Thread.sleep(10);
                    }
                }
                catch (IOException e) {
                    Client.this.stop();
                }
                catch (ClassNotFoundException e) {
                    Client.this.stop();
                }
                catch (InterruptedException e) {
                    Client.this.stop();
                }
            }
        }
    }

}
