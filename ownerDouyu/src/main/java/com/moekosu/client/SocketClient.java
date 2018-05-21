package com.moekosu.client;

import com.moekosu.util.STTUtil;

import javax.net.SocketFactory;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SocketClient {

	private static final String IP = "openbarrage.douyutv.com";
	private static final int PORT = 8601;

	private static final int OWNER_PORT = 8780;

	/**
	 * 长连接
	 */
	private static void connect(Map<String, String> map) throws Exception
	{
		Socket socket = new Socket("127.0.0.1", OWNER_PORT);

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader reader2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

		String respStr = "";
		while ((respStr = reader2.readLine()) != null) {
			System.out.println(respStr);
			writer.write(respStr);
			writer.flush();
		}
	}

	private static void shortConnect(Map<String, String> map) throws Exception
	{
		Socket socket = SocketFactory.getDefault().createSocket(IP, PORT);

		String content = STTUtil.encodeMultiValue(map);
		byte[] bytes = content.getBytes("utf-8");
		OutputStream os = socket.getOutputStream();
		os.write(bytes);

		socket.close();
	}

	private static void login(String roomid) throws Exception
	{
		Map<String, String> map = new HashMap<>();
		map.put("type", "loginreq");
		map.put("roomid", roomid);
		connect(map);
	}

	private static void heart() throws Exception
	{
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				Map<String, String> map = new HashMap<>();
				map.put("type", "mrkl");
				try {
					connect(map);
//					System.out.println("1");
				}
				catch (Exception e) {
					System.out.printf("this Thread send heart message error.");
				}
			}
		});

		while (true) {
			t.run();
			t.sleep(45000);
		}
	}

	private static void enterGroup(String roomid) throws Exception
	{
		Map<String, String> map = new HashMap<>();
		map.put("type", "joingroup");
		map.put("rid", roomid); // 房间号
		map.put("gid", "-9999"); // 海量弹幕模式
		connect(map);
	}

	public static void main(String[] args) throws Exception {
		heart();
	}

}
