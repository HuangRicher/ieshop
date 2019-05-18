package com.seamwhole.ssoserver.config;


import com.seamwhole.ssoserver.store.SsoLoginStore;
import com.seamwhole.ssoserver.util.JedisUtil;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class XxlSsoConfig implements InitializingBean, DisposableBean {

    @Value("${sso.redis.address}")
    private String redisAddress;

    @Value("${sso.redis.password}")
    private String password;

    @Value("${sso.redis.expire.minite}")
    private int redisExpireMinite;

    @Override
    public void afterPropertiesSet() throws Exception {
        SsoLoginStore.setRedisExpireMinite(redisExpireMinite);
        JedisUtil.init(redisAddress,password);
    }

    @Override
    public void destroy() throws Exception {
        JedisUtil.close();
    }

}
