package com.moekosu.design.abstractfactory;

/**
 * @author chenxu
 * @description
 * @project springboot-designModel
 * @date 2018/01
 */
public class MailSender implements Sender {

    @Override
    public void send()
    {
        System.out.println("this is mail send. /abstract factory");
    }

}
