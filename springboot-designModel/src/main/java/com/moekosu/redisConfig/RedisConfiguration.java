package com.moekosu.redisConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis配置类
 * 这里是基于引用了redis单独jar包的实现方式
 * 如果是引用了spring-boot-starter-redis的通用结合包，则是另外的更方便的实现方式
 */
@Configuration
public class RedisConfiguration {

    @Bean("jedis.pool")
    @Autowired
    public JedisPool jedisPool(@Qualifier("jedis.pool.config")JedisPoolConfig config,
                               @Value("${jedis.pool.host}")String host,
                               @Value("${jedis.pool.port}")int port)
    {
        return new JedisPool(config, host, port);
    }

    @Bean("jedis.pool.config")
    public JedisPoolConfig jedisPoolConfig(@Value("${jedis.pool.config.maxTotal}")int maxTotal,
                                           @Value("${jedis.pool.config.maxIdle}")int maxIdle,
                                           @Value("${jedis.pool.config.maxWaitMillis}")int maxWaitMillis)
    {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);

        return config;
    }

}
