package com.moekosu.util;

import java.util.Iterator;
import java.util.Map;

public class STTUtil {

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

}
