package com.moekosu.Thread;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

                //
                Request1 req = new Request1(in);
                req.parse();

                //
                Response1 res = new Response1(out);
                res.setRequest(req);
                res.send();

                socket.close();
            }
            catch (IOException e){
                continue;
            }
        }
    }

}
