package com.ruoyi;

import cn.hutool.extra.spring.SpringUtil;
import com.ruoyi.framework.config.SaTokenConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
//@ComponentScan("com.ruoyi.*")
public class RuoYiApplication
{

//       @Autowired
//    private ApplicationContext applicationContext;

    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);

//        String[] beanNames = applicationContext.getBeanNamesForType(HandlerInterceptor.class);
        Map<String, HandlerInterceptor> beanNames = SpringUtil.getBeansOfType(HandlerInterceptor.class);
        for (String beanName : beanNames.keySet())
        {
            System.out.println(beanName);
        }
        SaTokenConfigure bean = SpringUtil.getBean(SaTokenConfigure.class);
        System.out.println(bean);

        System.out.println("(♥◠‿◠)ﾉﾞ  若依启动成功   ლ(´ڡ`ლ)ﾞ ");
    }
}
