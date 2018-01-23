package com.moekosu.design.builder;

/**
 * @author chenxu
 * @description
 * @date 2018/01
 */
public class MailSender implements Sender {

    @Override
    public void send()
    {
        System.out.println("this is mail send. /builder");
    }

}
