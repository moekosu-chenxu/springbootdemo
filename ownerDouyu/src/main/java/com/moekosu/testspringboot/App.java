package com.moekosu.testspringboot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.moekosu.client.SocketClient;
import com.moekosu.constant.DouyuResp;
import com.moekosu.constant.DouyuRoom;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@SpringBootApplication
@MapperScan(basePackages = {"com.moekosu.dao"})
@ComponentScan(basePackages = {"com.moekosu.service"})
public class App {

    // 入口
    public static void main(String[] args) throws Exception
    {
//        SpringApplication.run(App.class, args);

//        get();

        //
        Map<String, String> map = new HashMap<>();
        SocketClient socketClient = new SocketClient();
        socketClient.connect(map);
    }

    public static void post() throws Exception {
        Map<String, String> param = new HashMap<String, String>();

        List<NameValuePair> list = new LinkedList<>();
        for (Map.Entry<String, String> e: param.entrySet()){
            list.add(new BasicNameValuePair(e.getKey(), e.getValue()));
        }

        String url = "http://open.douyucdn.cn/api/RoomApi/live/";
        String roomType = "19";
        HttpPost httpPost = new HttpPost(url + roomType);
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list, "UTF-8");
        httpPost.setEntity(formEntity);
        HttpClient client = org.apache.http.impl.client.HttpClients.createDefault();
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
     * get请求房间列表与房间信息
     * @throws Exception
     */
    public static void get() throws Exception
    {
        String url = "http://open.douyucdn.cn/api/RoomApi/live/";
        String roomType = "19";
        HttpGet httpGet = new HttpGet(url + roomType);

        HttpClient client = org.apache.http.impl.client.HttpClients.createDefault();
        HttpResponse response = null;
        try {
            response = client.execute(httpGet);
        }
        catch (Exception e){
            System.out.println("请求失败");
        }
        if(response.getStatusLine().getStatusCode() == 200){
            String resp = EntityUtils.toString(response.getEntity());
            System.out.println("请求成功: "+ resp);
            if(resp != null) {
                doJson(resp);
            }
        }
    }


    private static void doJson(String resp)
    {
        DouyuResp r = JSON.parseObject(resp, DouyuResp.class);
        List<DouyuRoom> list = r.getData();
        for (DouyuRoom room : list) {
            System.out.printf(room.getNickName() + "\n");
        }
    }





}
