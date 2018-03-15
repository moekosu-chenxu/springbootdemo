package com.moekosu.testspringboot;

import kafka.admin.AdminClient;
import org.junit.Test;

import java.util.Properties;

/**
 * @author chenxu
 * @date 2018/03
 */
public class KafkaTest {

    @Test
    public void test1()
    {
        Properties prop = new Properties();
        prop.put("bootstrap.servers", "192.168.180.128:9092"); //Kafka服务
        AdminClient adminClient = AdminClient.create(prop);
    }

}
