package com.moekosu.constant;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenxu
 * @date 2018/05
 */
public class KeepAlive implements Serializable {

    private static final long serialVersionUID = 3887166990812262467L;

    @Override
    public String toString()
    {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\t 维持连接包";
    }
}
