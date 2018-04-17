package com.moekosu.testspringboot;

/**
 * @author chenxu
 * @date 2018/04
 */
public class Text {

    static int i = 10;

    private static void m1()
    {
        System.out.println(j);
        System.out.println(i);
    }

    static{
        m1();
        System.out.println(i);
    }

    static int j = 20;

    public static void main(String[] args){
        System.out.println("main");
        m1();
    }

}
