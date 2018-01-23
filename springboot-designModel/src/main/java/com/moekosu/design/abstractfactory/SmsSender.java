package com.moekosu.design.abstractfactory;

/**
 * @author chenxu
 * @description
 * @project springboot-designModel
 * @date 2018/01
 */
public class SmsSender implements Sender {

    @Override
    public void send()
    {
        System.out.println("this is sms send. /abstract factory");
    }

}
