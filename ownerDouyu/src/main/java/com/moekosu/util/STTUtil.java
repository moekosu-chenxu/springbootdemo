package com.moekosu.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class STTUtil {

	/**
	 * 加密协议参数
	 * @param map
	 * @return
	 */
	public static String encodeMultiValue(Map<String, String> map)
	{
		if(map == null) {
			return "";
		}
		StringBuffer result = new StringBuffer();
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			if (result.length() > 0) {
				result.append("/");
			}
			Map.Entry entry = (Map.Entry) it.next();
			result.append(escape((String) entry.getKey()));
			result.append("@=");
			result.append(escape((String) entry.getValue()));
		}
		return result.toString();
	}

	/**
	 * 转义
	 * @param str
	 * @return
	 */
	private static String escape(String str)
	{
		// 转义/
		if(str.indexOf("/") > -1) {
			StringBuffer buf = new StringBuffer(str.length()*2);
			String[] arrs = str.split("/");
			for (String arr : arrs) {
				if(buf.length() > 0) {
					buf.append("@S");
				}
				buf.append(arr);
			}
			str = buf.toString();
		}
		// 转义@
		if(str.indexOf("@") > -1) {
			StringBuffer buf2 = new StringBuffer(str.length()*2);
			String[] arrs2 = str.split("@");
			for (String arr2 : arrs2) {
				if(buf2.length() > 0) {
					buf2.append("@A");
				}
				buf2.append(arr2);
			}
			str = buf2.toString();
		}
		return str;
	}

	/**
	 * 解析返回参数
	 * @param resp
	 * @return
	 */
	public static Map<String, String> decodeResp(String resp)
	{
		Map<String, String> map = new HashMap<>();

		String[] resps = resp.split("[/]");
		for(String params : resps) {
			String[] param = params.split("@=");
			String key = param[0];
			String value = param[1];
			map.put(key, value);
		}

		return map;
	}

}
