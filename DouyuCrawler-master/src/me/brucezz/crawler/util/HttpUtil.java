package me.brucezz.crawler.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Brucezz on 2016/01/04.
 * DouyuCrawler
 */
public class HttpUtil {

    /**
     * GET方法
     */
    public static String get(String url) {

        HttpURLConnection conn = null;

        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(10 * 1000); //超时值
            conn.setReadTimeout(20 * 1000); //读超时
            // 参数设置
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("GET");
            // 测试重定向301问题
//            conn.setInstanceFollowRedirects(false);
            
            // 打开连接
            conn.connect();
            
            LogUtil.d("connection url1: "+ conn.getURL());
            LogUtil.d("connection status: "+ conn.getResponseCode());
            LogUtil.d("connection url2: "+ conn.getURL());
            LogUtil.d("connection location: "+ conn.getHeaderField("location"));
            
            InputStream in = conn.getInputStream();

            LogUtil.d("HTTP GET", url);

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            reader.close();
            in.close();

            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }
}
