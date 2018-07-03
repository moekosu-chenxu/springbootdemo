package com.moekosu.tabs;

import java.lang.annotation.*;

/**
 * 自定义注解
 * @author chenxu
 * @date 2018/06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
//@Inherited //子类继承
//@Documented //记录进注释JavaDoc
public @interface TestPath {

    String value() default "";

}
