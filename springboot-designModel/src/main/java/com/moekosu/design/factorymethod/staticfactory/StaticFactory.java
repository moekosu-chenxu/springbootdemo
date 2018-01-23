package com.moekosu.design.factorymethod.staticfactory;

import com.moekosu.design.factorymethod.normalfactory.MailSender;
import com.moekosu.design.factorymethod.normalfactory.Sender;
import com.moekosu.design.factorymethod.normalfactory.SmsSender;

/**
 * @author chenxu
 * @description
 * @project springboot-designModel
 * @date 2018/01
 */
public class StaticFactory {

    public static Sender mailSender()
    {
        return new MailSender();
    }

    public static Sender smsSender()
    {
        return new SmsSender();
    }

}
