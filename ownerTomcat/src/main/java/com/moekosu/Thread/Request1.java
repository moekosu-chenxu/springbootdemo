package com.moekosu.Thread;

import com.alibaba.druid.util.StringUtils;
import com.moekosu.config.ServerConfig;
import com.moekosu.logger.ServerLogger;
import com.moekosu.logger.ServerLoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author chenxu
 * @date 2018/04
 */
public class Request1 {

    private static final ServerLogger logger = ServerLoggerFactory.getInstance();

    private String method;
    private String uri;
    private String protocol;

    private Map<String, String> header;
    private Map<String, String> paramsGet;
    private Map<String, String> paramsPost;

    public Request1(InputStream in)
    {
        this.header = new LinkedHashMap<>();//保存了记录的插入顺序，Iterator遍历时必然先插入先取出
        this.paramsGet = new HashMap<>();
        this.paramsPost = new HashMap<>();
        parse(in);
    }

    /**
     * 解析url请求
     */
    private void parse(InputStream in)
    {
        try{
            logger.debug("parse request start.");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String request = br.readLine();

            // 1.解析基础信息(method, uri, protocol)
            if(request != null && request.length() > 0){
                String[] reqGroup = request.split("\\s"); //空格分开
                this.method = reqGroup[0];
                this.uri = reqGroup[1];
                // 包含参数
                if(this.uri.indexOf("?") > -1){
                    parseParemeters(this.uri.split("[?]")[1], false);
                    this.uri = this.uri.split("[?]")[0];
                }
                // 根目录
                if(this.uri.endsWith("/")){
                    this.uri += ServerConfig.getWelcome_page();
                }
                this.protocol = reqGroup[2];
            }
            logger.debug("parse1 complete: method: "+ this.method+ " , uri: "+ this.uri+ " , protocol: "+ this.protocol);

            // 2.解析头部信息
            logger.debug("parse2 for header start..");
            String tempPost;
            int len = 0;
            // 读一行
            while ((tempPost = br.readLine()) != null){
                if(StringUtils.isEmpty(tempPost)){
                    break;
                }
                // 第一个冒号位置
                int seqPosition = tempPost.indexOf(":");
                String k = tempPost.substring(0, seqPosition).trim();
                String v = tempPost.substring(seqPosition+1).trim();
                this.header.put(k, v);
                // 获取传送参数长度
                if(k.indexOf("Content-Length") > -1) {
                    len = Integer.valueOf(v);
                }
            }
            logger.debug("parse2 for header complete..");

            // 3.解析post参数
            logger.debug("parse3 for post data start...");
            if(StringUtils.equals(this.method, "POST") && len > 0) {
                char[] data = new char[len];
                br.read(data);
                String data_ = new String(data);
                parseParemeters(data_, true);
            }
            logger.debug("parse3 for post data complete...");
        }
        catch (Exception e){
            logger.error("request parse error.", e);
        }
    }

    /**
     * 解析参数
     * @param parameters 参数字符串
     * @param isPost post数据
     */
    private void parseParemeters(String parameters, boolean isPost)
    {
        String[] params = parameters.split("[&]");
        for(String param : params){
            String[] arr = param.split("[=]");
            if (isPost) {
                this.paramsPost.put(arr[0], arr[1]);
            }
            else {
                this.paramsGet.put(arr[0], arr[1]);
            }
        }
    }

    public void parse2(InputStream in)
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
