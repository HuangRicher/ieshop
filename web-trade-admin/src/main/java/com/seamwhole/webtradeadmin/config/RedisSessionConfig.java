package com.seamwhole.webtradeadmin.config;

import org.springframework.context.annotation.Configuration;
//import org.springframework.social.connect.web.HttpSessionSessionStrategy;
/**
 * session托管到redis
 * 分布式Session，使用spring.session.store-type=redis自动配置
 */
//@Configuration
//使用springboot-session处理，单位：秒；
//RedisFlushMode有两个参数：ON_SAVE（表示在response commit前刷新缓存），IMMEDIATE（表示只要有更新，就刷新缓存）
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 1800, redisFlushMode = RedisFlushMode.ON_SAVE, redisNamespace = "myproject")
//@EnableRedisHttpSession
public class RedisSessionConfig {
    /*@Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }*/
}