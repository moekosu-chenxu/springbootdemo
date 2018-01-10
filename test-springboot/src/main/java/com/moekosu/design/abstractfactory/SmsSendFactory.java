package com.moekosu.design.abstractfactory;

/**
 * @author chenxu
 * @description
 * @project springboot-designModel
 * @date 2018/01
 */
public class SmsSendFactory implements Provider {

    @Override
    public Sender produce()
    {
        return new SmsSender();
    }

}
