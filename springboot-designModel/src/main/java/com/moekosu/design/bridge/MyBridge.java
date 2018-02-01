package com.moekosu.design.bridge;

/**
 * @author chenxu
 * @date 2018/02
 */
public class MyBridge extends Bridge {

    public void method()
    {
        getSource().method();
    }

}
