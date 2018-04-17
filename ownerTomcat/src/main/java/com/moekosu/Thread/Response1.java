package com.moekosu.Thread;

import com.moekosu.logger.ServerLogger;
import com.moekosu.logger.ServerLoggerFactory;
import com.sun.net.httpserver.HttpServer;

import java.io.*;

/**
 * @author chenxu
 * @date 2018/04
 */
public class Response1 {

    private static final ServerLogger logger = ServerLoggerFactory.getInstance();

    private static final String WEB_ROOT = "D:\\tomdog\\webapps";
    private static final int BUFFER_LEN = 1024;

    private Request1 request;

    private OutputStream out;

    public Response1(OutputStream out)
    {
        this.out = out;
    }

    public void setRequest(Request1 request) {
        this.request = request;
    }

    public void send()
    {
        byte[] buf = new byte[BUFFER_LEN];
        FileInputStream in = null;

        try {
            File file = new File(WEB_ROOT, request.getUri());
            if(file.exists()) {
                in = new FileInputStream(file);
                int len;
                while ((len = in.read(buf, 0, BUFFER_LEN)) != -1) {
                    out.write(buf, 0, len);
                }
            }
            else{
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                out.write(errorMessage.getBytes());
            }
        }
        catch (FileNotFoundException e) {
            logger.error("parse response error: file not found", e);
        }
        catch (IOException e) {
            logger.error("parse response error: io exception", e);
        }
        finally {
            if(in != null){
                try {
                    in.close();
                }
                catch (IOException e){
                    logger.error("response1 close input stream error", e);
                }
            }
        }
    }

}
