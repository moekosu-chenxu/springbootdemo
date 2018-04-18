package com.moekosu.Thread;

import com.moekosu.logger.ServerLogger;
import com.moekosu.logger.ServerLoggerFactory;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.util.*;

/**
 * @author chenxu
 * @date 2018/04
 */
public class Response1 {

    private static final ServerLogger logger = ServerLoggerFactory.getInstance();

    private static final String WEB_ROOT = "D:\\tomdog\\webapps";
    private static final int BUFFER_LEN = 1024;
    private static final String PROTOCOL = "HTTP/1.1";

    private static final int STATUS_OK = 200;
    private static final String DESC_OK = "OK";
    private static final int STATUS_NOT_FOUND = 404;
    private static final String DESC_NOT_FOUND = "Page Not Found";
    private static final int STATUS_ERROR = 500;
    private static final String DESC_ERROR = "Internal Server Error";

    private Map<Integer, String> statusMap;

    private Integer status;

    private Map<String, String> header;

    private OutputStream out;

    /**
     * 初始化头部信息
     */
    public Response1(OutputStream out)
    {
        this.out = out;
        this.header = new LinkedHashMap<>();
        this.statusMap = new HashMap<>();

        statusMap.put(STATUS_OK, DESC_OK);
        statusMap.put(STATUS_NOT_FOUND, DESC_NOT_FOUND);
        statusMap.put(STATUS_ERROR, DESC_ERROR);

        header.put("Content-Type", "text/plain;charset=utf-8");
        header.put("Date", new Date().toString());
    }

    /**
     * 打印输出视图
     */
    public void write(byte[] bytes)
    {
        header.put("Content-Length", Integer.toString(bytes.length));

        PrintStream ps = new PrintStream(out);
        printHeader(ps);
        try {
            ps.write(bytes);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        ps.flush();
    }

    /**
     * 打印头信息
     */
    private void printHeader(PrintStream ps)
    {
        ps.println(PROTOCOL + " " + status + " " + statusMap.get(status));

        Set<Map.Entry<String, String>> set =  header.entrySet();
        for (Map.Entry<String, String> e : set){
            ps.println(e.getKey() + ":" + e.getValue());
        }
        ps.println("");
    }

    /**
     * 设置返回状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * 设置返回头
     */
    public void setHeader(String key, String value) {
        this.header.put(key, value);
    }
}
