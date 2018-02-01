package com.moekosu.design.bridge.sqlDriver;

/**
 * @author chenxu
 * @date 2018/02
 */
public interface Driver {

    MyConnection connect(String url, SqlInfo info);

}
