package com.seamwhole.serviceuserone.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfig {

    @Bean
    public DistributedLockFactoryBean distributeLockTemplate(){
        DistributedLockFactoryBean d  = new DistributedLockFactoryBean();
        d.setMode("SINGLE");
        return d;
    }
}
