package com.moekosu.testspringboot;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import org.apache.http.client.methods.HttpPost;

/**
 * @author chenxu
 * @date 2018/01
 */
public class NormalTest {

    interface bizProxy{
        void process();
    }
    interface ActProxy {
        bizProxy getProxy();
    }
    class commonBiz implements bizProxy{
        private ActProxy act = null;
        public commonBiz(ActProxy act){
            this.act = act;
        }
        public void process() {
            System.out.println("commonBiz.process");
        }
    }
    class bizA implements bizProxy{
        public void process() {
            System.out.println("bizA.process");
        }
    }

    /**
     * proxy Test
     */
    @Test
    public void test()
    {
        bizProxy biz = new commonBiz(new ActProxy(){
            public bizProxy getProxy(){
                return new bizA();
            }
        });
    }

    /**
     * File Test
     */
    @Test
    public void test2()
    {
        File file = new File("xx.zip");
        if(!file.exists()){

        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
        }
        catch ( Exception e ){

        }
    }

    /**
     * httpClient Test
     */
    @Test
    public void test3()  throws Exception
    {
        Map<String, String> param = new HashMap<String, String>();
        param.put("version", "1.0");
        param.put("portalType", "WAP");
        param.put("portalId", "100");
        param.put("reqTime", "2016-04-08 18:39:00");
        param.put("transactionId", "20160408183900000001");
        param.put("sign", "ETSZD4361xdryq");
        param.put("funcCode", "getActivityDetail");
        param.put("activityId", "ACT20171221317691980");

        List<NameValuePair> list = new LinkedList<>();
        for (Map.Entry<String, String> e: param.entrySet()){
            list.add(new BasicNameValuePair(e.getKey(), e.getValue()));
        }

        String url = "http://10.12.3.213:28085/dccp-platform/oss.ajax";
        HttpPost httpPost = new HttpPost(url);
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list, "UTF-8");
        httpPost.setEntity(formEntity);
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = null;
        try {
            response = client.execute(httpPost);
        }
        catch (Exception e){
            System.out.println("请求失败");
        }
        if(response.getStatusLine().getStatusCode() == 200){
            String resp = EntityUtils.toString(response.getEntity());
            System.out.println("请求成功: "+ resp);
        }
    }

    /**
     * 面试题 Test
     */
    @Test
    public void test4()
    {
        int a = Integer.parseInt("12");
        int b = Integer.valueOf("12").intValue();
        System.out.println(a == b);

        int a1 = 1;
        float b1 = 2.0F;
        System.out.println(a1/b1);
    }

}
