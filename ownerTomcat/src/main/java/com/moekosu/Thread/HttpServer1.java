package com.moekosu.Thread;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟server
 * @author chenxu
 * @date 2018/04
 */
public class HttpServer1 {

    public static final String WEB_ROOT = "d://tomdog" + File.separator + "webapps";

    public static void main(String[] args) {
        HttpServer1 server = new HttpServer1();
        server.await();
    }

    public void await()
    {
        ServerSocket serverSocket = null;
        int port = 8780;
        try{
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        }
        catch (IOException e){
            System.exit(1);
        }

        while (true)
        {
            Socket socket = null;
            InputStream in = null;
            OutputStream out = null;
            try{
                socket = serverSocket.accept();
                in = socket.getInputStream();
                out = socket.getOutputStream();

                // 解析request数据
                Request1 req = new Request1(in);
                req.parse();

                // 处理返回视图数据
                Response1 res = new Response1(out);
                res.setHeader("Content-Type", "text/html;charset=UTF-8");

                File file = new File(WEB_ROOT + req.getUri());

                if (!file.exists()) {
                    res.setStatus(404);
                    file = new File(WEB_ROOT + "/error.html");
                } else {
                    res.setStatus(200);
                }

                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                byte[] bys = new byte[(int) file.length()];
                bis.read(bys);

                res.write(bys);

                bis.close();
            }
            catch (IOException e){
                continue;
            }
        }
    }

}
