package com.moekosu.design.abstractfactory;

/**
 * @author chenxu
 * @description
 * @project springboot-designModel
 * @date 2018/01
 */
public class MailSendFactory implements Provider {

    @Override
    public Sender produce()
    {
        return new MailSender();
    }

}
