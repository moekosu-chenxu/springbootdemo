package com.moekosu.design.factorymethod.normalfactory;

/**
  * @author moekosu
  * @description MailSender
  * @date 2018/1/10
  */
public class MailSender implements Sender{

    /**
      * @author moekosu
      * @description 自定义邮件发送
      * @date 2018/1/10
      */
    @Override
    public void send(){
        System.out.println("this is mail send..");
    }


}
