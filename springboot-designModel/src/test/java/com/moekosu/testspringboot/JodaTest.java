package com.moekosu.testspringboot;

import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Date;
import java.util.Locale;

/**
 * Joda-Time 时间控件
 * @author chenxu
 * @date 2018/01
 */
public class JodaTest {

    /**
     * DateTime测试1：简单输出测试
     */
    @Test
    public void dateTimeTest1()
    {
        // 无参数，当前时间
        DateTime dateTime1 = new DateTime();
        System.out.println(dateTime1); // out: 2018-01-23T09:22:22.832+08:00

        // 设置固定时间
        DateTime dateTime2 = new DateTime(2016,2,14,0,0,0);
        System.out.println(dateTime2); // out: 2016-02-14T00:00:00.000+08:00

        // 传入一个Long类型的参数，表示距离[参照时间1970/01/01T00:00:00Z]的毫秒数
        DateTime dateTime3 = new DateTime(1456473917004L);
        System.out.println(dateTime3); // out: 2016-02-26T16:05:17.004+08:00

        // 通过一个Object[限制ReadableInstant, String, Calendar和Date类型] 创建时间
        DateTime dateTime4 = new DateTime(new Date());
        System.out.println(dateTime4); // out: 2018-01-23T09:22:23.042+08:00

        DateTime dateTime5 = new DateTime("2016-02-15T00:00:00.000+08:00");
        System.out.println(dateTime5); // out: 2016-02-15T00:00:00.000+08:00
    }

    /**
     * DateTime测试2：改变年份，加减时间(天数,月数)
     */
    @Test
    public void dateTimeTest2()
    {
        // 1.改变年份(PS：如果当年不存在那天，如02.29，则会自动转换为28号)
        DateTime dateTime = new DateTime(2016,2,14,0,0,0);
        System.out.println(dateTime); // out: 2016-02-14T00:00:00.000+08:00
        DateTime dateTimeChangeYear = dateTime.withYear(2018);
        System.out.println(dateTimeChangeYear); // out: 2018-02-14T00:00:00.000+08:00

        // 2.
        DateTime dateTime1 = new DateTime();
        DateTime dateTime1plus = dateTime1.plusDays(1); // +1天
        System.out.println(dateTime1plus);
        DateTime dateTime1minus = dateTime1.minusMonths(1); // -1个月
        System.out.println(dateTime1minus);
    }

    /**
     * DateTime测试3：星期 月份 的提取
     */
    @Test
    public void dateTimeTest3()
    {
        DateTime now = new DateTime(); // 2018-01-23T09:45:38.611+08:00
        // monthOfYear 当前时间所在月份(根据服务器语言显示)
        System.out.println(now.monthOfYear().getAsText()); // 一月
        // 根据选定的语言显示月份
        System.out.println(now.monthOfYear().getAsText(Locale.KOREAN)); // 1월
        // dayOfWeek 星期
        System.out.println(now.dayOfWeek().getAsShortText()); // 星期二
        System.out.println(now.dayOfWeek().getAsShortText(Locale.CHINESE)); // 星期二
    }

}
