package com.moekosu.design.adapter.classadapter;

/**
 * 实现适配器中心的新方法
 * @author chenxu
 * @date 2018/01
 */
public class ClassAdapter extends Source implements Targetable {

    @Override
    public void method2()
    {
        System.out.println("ObjectAdapter.method2");
    }

}
