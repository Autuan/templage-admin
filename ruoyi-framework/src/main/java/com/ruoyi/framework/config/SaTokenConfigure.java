package com.ruoyi.framework.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@Slf4j
public class SaTokenConfigure implements WebMvcConfigurer {
//    @Autowired
//    private TokenService tokenService;


    // 注册sa-token的拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("SaTokenConfigure -> addInterceptors -> run");

        registry.addInterceptor(new SaInterceptor(handler -> {
            log.info("SaInterceptor run -> {}",StpUtil.getTokenInfo());
                    StpUtil.checkLogin();
                }))
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/login",

//                        "/getInfo",
                        "/logout",
//                        "/getRouters",

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
                        "/druid/**")
                        ;
        log.info("registry.addInterceptor end");
    }
}