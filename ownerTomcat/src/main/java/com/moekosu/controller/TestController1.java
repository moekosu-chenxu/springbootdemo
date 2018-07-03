package com.moekosu.controller;


import com.moekosu.tabs.TestPath;

/**
 * 自定义的Controller
 * 默认带TestPath注解，注册请求路径，返回String，匹配webapps下html后缀文件
 * @author chenxu
 * @date 2018/06
 */
public class TestController1 {

    @TestPath("/zz.do")
    public String test1()
    {
        System.out.println("调用到了test1方法");
        return "test";
    }

}
