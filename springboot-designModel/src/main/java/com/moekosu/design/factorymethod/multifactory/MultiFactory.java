package com.moekosu.design.factorymethod.multifactory;

import com.moekosu.design.factorymethod.normalfactory.MailSender;
import com.moekosu.design.factorymethod.normalfactory.Sender;
import com.moekosu.design.factorymethod.normalfactory.SmsSender;

/**
 * @author chenxu
 * @description
 * @project springboot-designModel
 * @date 2018/01
 */
public class MultiFactory {

    public Sender getMailSender()
    {
        return new MailSender();
    }

    public Sender getSmsSender()
    {
        return new SmsSender();
    }

}
