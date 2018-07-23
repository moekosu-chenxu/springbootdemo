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

    private static Map<String, String> type_map = new LinkedHashMap<>();
    private static int port;
    private static String protocol;
    private static String web_root;
    private static String error_page;
    private static String welcome_page;
    private static String page_suffix;

    private static final String TYPE_SUFFIX = "suffix";
    private static final String TYPE_PACKAGE = "type";

    static{
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read("./src/main/resources/config.xml");
            // 根目录
            Element root = doc.getRootElement();

            Element configs = root.element("config");
            Element mappings = root.element("type-mappings");

            port = Integer.valueOf(configs.elementText("port")); // 默认端口
            protocol = configs.elementText("protocol");
            web_root = configs.elementText("webroot");
            error_page = configs.elementText("error-page");
            welcome_page = configs.elementText("welcome-page");
            page_suffix = configs.elementText("page-suffix");

            List<Element> mapping = mappings.elements("type-mapping");
            for (Element mapp : mapping){
                type_map.put(mapp.attributeValue(TYPE_SUFFIX), mapp.attributeValue(TYPE_PACKAGE));
            }
        }
        catch (DocumentException e){
            e.printStackTrace();
        }
    }

    public static String getTypeConfig(String key)
    {
        return type_map.get(key);
    }

    public static void setPort(int port_)
    {
        port = port_;
    }
    public static int getPort()
    {
        return port;
    }
    public static String getProtocol()
    {
        return protocol;
    }
    public static String getWeb_root()
    {
        return web_root;
    }
    public static String getError_page()
    {
        return error_page;
    }
    public static String getWelcome_page(){
        return welcome_page;
    }

    public static String getPage_suffix() {
        return page_suffix;
    }
}
