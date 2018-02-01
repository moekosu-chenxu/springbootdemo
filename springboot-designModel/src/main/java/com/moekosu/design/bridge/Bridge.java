package com.moekosu.design.bridge;

/**
 * @author chenxu
 * @date 2018/02
 */
public class Bridge {

    private Sourceable source;

    public void method()
    {
        source.method();
    }

    public void setSource(Sourceable source)
    {
        this.source = source;
    }

    public Sourceable getSource() {
        return source;
    }
}
