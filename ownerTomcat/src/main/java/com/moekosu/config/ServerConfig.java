package com.moekosu.config;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileInputStream;
import java.util.*;

public class ServerConfig {

    private static Map<String, String> map = new LinkedHashMap<>();

    private static int port;

    public static final String PORT = "port";
    public static final String PROTOCOL = "protocol";
    public static final String WEB_ROOT = "webroot";
    public static final String ERROR_PAGE = "error-page";
    private static final String TYPE_SUFFIX = "suffix";
    private static final String TYPE_PACKAGE = "type";
    public static final String TYPE_HTML = "html";
    public static final String TYPE_CSS = "css";
    public static final String TYPE_JS = "js";
    public static final String TYPE_JSON = "json";
    public static final String TYPE_JPG = "jpg";
    public static final String TYPE_PNG = "png";
    public static final String TYPE_GIF = "gif";

    static{
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read("./src/main/resources/config.xml");
            // 根目录
            Element root = doc.getRootElement();

            Element configs = root.element("config");
            Element mappings = root.element("type-mappings");

            map.put(PORT, configs.elementText(PORT));
            port = Integer.valueOf(configs.elementText(PORT)); // 默认端口
            map.put(PROTOCOL, configs.elementText(PROTOCOL));
            map.put(WEB_ROOT, configs.elementText(WEB_ROOT));
            map.put(ERROR_PAGE, configs.elementText(ERROR_PAGE));

            List<Element> mapping = mappings.elements("type-mapping");
            for (Element mapp : mapping){
                map.put(mapp.attributeValue(TYPE_SUFFIX), mapp.attributeValue(TYPE_PACKAGE));
            }
        }
        catch (DocumentException e){
            e.printStackTrace();
        }
    }

    public static String getConfig(String key)
    {
        return map.get(key);
    }

    public static void setPort(int port_)
    {
        port = port_;
    }
    public static int getPort()
    {
        return port;
    }

}
