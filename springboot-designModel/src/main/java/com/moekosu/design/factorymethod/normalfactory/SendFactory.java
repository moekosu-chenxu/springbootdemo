package com.moekosu.design.factorymethod.normalfactory;

import org.springframework.util.StringUtils;

/**
  * @author moekosu
  * @description SendFactory 发送工厂
  * @date 2018/1/10
  */
public class SendFactory {

    private final String MAIL_TYPE = "mail";
    private final String SMS_TYPE = "sms";

    /**
      * @author moekosu
      * @description 获取Sender对象
      * @param type 类型
      * @return 具体Sender对象
      * @date 2018/1/10
      */
    public Sender getInstance(String type)
    {
        if(StringUtils.isEmpty(type)){
            return null;
        }

        if(MAIL_TYPE.equals(type))
        {
            return new MailSender();
        }
        else if(SMS_TYPE.equals(type))
        {
            return new SmsSender();
        }
        return null;
    }

}
