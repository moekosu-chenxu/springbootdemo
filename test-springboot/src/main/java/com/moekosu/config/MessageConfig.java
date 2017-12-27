package com.moekosu.config;

import org.springframework.beans.factory.annotation.Value;

import java.io.FileInputStream;
import java.util.Properties;

public class MessageConfig {

    @Value("${systemConfig.userDir}")
    private String USER_DIR;

    private static MessageConfig messageConfig;
    private static Properties messages = null;

    /**
     * 初始化读取config文件
     */
    private MessageConfig()
    {
        String url = USER_DIR + "message.properties";

        messages = new Properties();
        FileInputStream in = null;

        try {
            in = new FileInputStream(url);
            messages.load(in);
        }
        catch (Exception e){
            // TODO logging fail
        }
        finally {
            if(in != null){
                try {
                    in.close();
                }
                catch (Exception e){
                    // TODO logging fail
                }
            }
        }
    }

    /**
     * 单例 获取配置Config对象
     * @return
     */
    public synchronized static MessageConfig getInstance()
    {
        if (messageConfig == null || messages == null)
        {
            messageConfig = new MessageConfig();
        }
        return messageConfig;
    }
}
