package com.ruoyi.framework.config;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouterUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    @Autowired
    private TokenService tokenService;


    // 注册sa-token的拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册路由拦截器，自定义验证规则 
        registry.addInterceptor(new SaRouteInterceptor((request, response, handler) -> {
            LoginUser loginUser = tokenService.getLoginUser(request);
            // 登录验证 -- 排除多个路径
            SaRouterUtil.match(
                    //获取所有的
                    Arrays.asList("/**"),
                    //排除下不需要拦截的
                    Arrays.asList(
                            "/login",
                            "/captchaImage",
                            "/*.html",
                            "/**/*.html",
                            "/**/*.css",
                            "/**/*.js",
                            "/profile/**",
                            "/common/download**",
                            "/common/download/resource**",
                            "/swagger-ui.html",
                            "/swagger-resources/**",
                            "/webjars/**",
                            "/*/api-docs",
                            "/druid/**"),
                    () -> {
                        if(StringUtils.isNotNull(loginUser) ) {
                            StpUtil.setLoginId(1);
                            tokenService.verifyToken(loginUser);
                        }
                        else {
                         throw new CustomException(StringUtils.format("请求访问：{}，认证失败，无法访问系统资源",request.getRequestPath()), HttpStatus.UNAUTHORIZED);
                        }
                    });
        })).addPathPatterns("/**");

        registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**");
    }
}