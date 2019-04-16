package com.seamwhole.servicetradecore.resolver;

import com.seamwhole.servicetradecore.annotation.LoginUser;
import com.seamwhole.servicetradecore.interceptor.AuthorizationInterceptor;
import com.seamwhole.servicetradecore.model.ShopUser;
import com.seamwhole.servicetradecore.service.ShopUserService;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-23 22:02
 */
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private ShopUserService shopUserService;

    public void setUserService(ShopUserService shopUserService) {
        this.shopUserService = shopUserService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(ShopUser.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        //获取用户ID
        Object object = request.getAttribute(AuthorizationInterceptor.LOGIN_USER_KEY, RequestAttributes.SCOPE_REQUEST);
        if (object == null) {
            return null;
        }

        //获取用户信息
        ShopUser user = shopUserService.queryObject((Integer) object);

        return user;
    }
}
