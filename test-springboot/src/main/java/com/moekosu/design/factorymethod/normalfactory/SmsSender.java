package com.moekosu.design.factorymethod.normalfactory;

/**
  * @author moekosu
  * @description SmsSender
  * @date 2018/1/10
  */
public class SmsSender implements Sender{

    /**
      * @author moekosu
      * @description 自定义短信发送
      * @date 2018/1/10
      */
    @Override
    public void send()
    {
        System.out.println("this is sms send..");
    }

}
