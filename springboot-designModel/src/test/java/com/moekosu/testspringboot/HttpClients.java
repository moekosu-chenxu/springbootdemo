package com.moekosu.testspringboot;
import org.apache.http.HttpResponse;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HttpClients {

	@Test
	public void post() throws Exception {
		Map<String, String> param = new HashMap<String, String>();
//		param.put("version", "1.0");
//		param.put("portalType", "WAP");
//		param.put("portalId", "100");
//		param.put("reqTime", "2016-04-08 18:39:00");
//		param.put("transactionId", "20160408183900000001");
//		param.put("sign", "ETSZD4361xdryq");
//		param.put("funcCode", "getActivityDetail");
//		param.put("activityId", "ACT20171221317691980");

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

	@Test
	public void get() throws Exception
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
		}
	}

}
