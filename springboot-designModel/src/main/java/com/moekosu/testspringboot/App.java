package com.moekosu.testspringboot;

import com.moekosu.constant.UserLogin;
import com.moekosu.redisConfig.RedisClient;
import com.moekosu.service.LotteryService;
import com.moekosu.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试spring boot结合mybatis连接mysql数据库
 */
@RestController
// 主入口
@SpringBootApplication
// 扫描mapper
@MapperScan(basePackages = {"com.moekosu.dao"})
// 扫描Component(包含controller, service, redis)
@ComponentScan(basePackages = {"com.moekosu.controller", "com.moekosu.service", "com.moekosu.redisConfig"})
// 开启事务管理
//@EnableTransactionManagement
public class App {

    /**
     * log4j：Spring boot 1.3以下低版本使用的日志包
     * 高版本使用log4j2 TODO 待修改
     */
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    @Autowired
    private UserService userService;

    // TODO Redis未测试
//    @Autowired
//    private RedisClient redisClient;

    // 入口
    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }

    @RequestMapping("/getMsg")
    @ResponseBody
    public String getUserLoginMsgList()
    {
        String result = "";
        List<UserLogin> list = userService.getUserLoginMsgList();

        for (UserLogin u : list)
        {
            result += u.toString();
        }

        // TODO 在console打出信息，没有输出log文件
        logger.info("----------query user login msg list by db-----------");

        return result;
    }

    /**
     * Redis set值测试
     * @param key    input
     * @param value  input
     */
//    @RequestMapping("/redisSet")
//    public void set(String key, String value)
//    {
//        try{
//            redisClient.set(key, value);
//        }
//        catch (Exception e){
//            logger.error("redis occur some error: cannot set "+value +" into "+key+" .");
//        }
//    }

    /**
     * Redis get值测试
     * @param key input
     * @return
     */
//    @RequestMapping("/redisGet")
//    @ResponseBody
//    public String get(String key)
//    {
//        try{
//            return redisClient.get(key);
//        }
//        catch (Exception e){
//            logger.error("redis occur some error: cannot get " + key);
//            return null;
//        }
//    }
}
