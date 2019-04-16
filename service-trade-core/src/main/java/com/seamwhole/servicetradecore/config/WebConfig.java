package com.seamwhole.servicetradecore.config;


import com.seamwhole.servicetradecore.interceptor.AuthorizationInterceptor;
import com.seamwhole.servicetradecore.resolver.LoginUserHandlerMethodArgumentResolver;
import com.seamwhole.servicetradecore.service.ShopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


/**
 * 注册外部的interceptor
 */
@Configuration
public class WebConfig  implements WebMvcConfigurer {
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Autowired
    private ShopUserService shopUserService;


    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        LoginUserHandlerMethodArgumentResolver resolver=new LoginUserHandlerMethodArgumentResolver();
        resolver.setShopUserService(shopUserService);
        resolvers.add(resolver);
    }
}

