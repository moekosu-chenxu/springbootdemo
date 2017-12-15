package com.moekosu.redisConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 暴露出的Redis操作接口
 * @throws Exception 显式的在方法体中定义抛出异常，强制命令调用接口处理异常
 */
@Component
public class RedisClient {

    @Autowired
    private JedisPool jedisPool;

    public void set(String key, String value) throws Exception
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        }
        finally {
            jedis.close();
        }
    }

    public String get(String key) throws Exception
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }
        finally {
            jedis.close();
        }
    }

}
