package com.moekosu.client;

import com.moekosu.util.STTUtil;

import javax.net.SocketFactory;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

public class SocketClient {

	private static final String ip = "openbarrage.douyutv.com";
	private static final int port = 8601;

	/**
	 * 长连接
	 */
	public void connect(Map<String, String> map) throws Exception
	{
		Socket socket = SocketFactory.getDefault().createSocket(ip, port);

		String content = STTUtil.encodeMultiValue(map);
		byte[] bytes = content.getBytes("utf-8");
		OutputStream os = socket.getOutputStream();
		os.write(bytes);

		socket.close();
	}

}
