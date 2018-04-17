package com.moekosu.Thread;

import com.moekosu.logger.ServerLogger;
import com.moekosu.logger.ServerLoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author chenxu
 * @date 2018/04
 */
public class Request1 {

    private static final ServerLogger logger = ServerLoggerFactory.getInstance();

    private InputStream in;

    private String uri;

    public Request1(InputStream in)
    {
        this.in = in;
    }

    /**
     * 解析url请求
     */
    public void parse()
    {
        StringBuffer request = new StringBuffer(2048);
        int i = 0;
        byte[] buffer = new byte[2048];
        try {
            i = in.read(buffer);
        }
        catch (IOException e){
            logger.error("Request1.parse read request error.", e);
        }
        for (int j=0; j< i; j++){
            request.append((char)buffer[j]);
        }
        logger.debug("final request string: " + request.toString());
        uri = parseUri(request.toString());
    }

    /**
     * 解析出uri
     */
    private String parseUri(String reqStr)
    {
        int index1, index2;
        // 第一个空格的位置
        index1 = reqStr.indexOf(" ");
        if(index1 != -1){
            // 第二个空格的位置
            index2 = reqStr.indexOf(" ", index1 + 1);
            if(index2 > index1){
                // 中间的字符串
                return reqStr.substring(index1, index2).trim();
            }
        }
        return null;
    }

    public String getUri()
    {
        return uri;
    }

}
