package com.moekosu.design.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenxu
 * @date 2018/01
 */
public class Builder {

    private List<Sender> list = new ArrayList<Sender>();

    public void produceMailSender(int num)
    {
        for(int i= 0; i< num; i++)
        {
            list.add(new MailSender());
        }
    }

    public void produceSmsSender(int num)
    {
        for(int i= 0; i< num; i++)
        {
            list.add(new SmsSender());
        }
    }

    public List<Sender> getBuilderList()
    {
        return list;
    }

}
