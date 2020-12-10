package com.autuan.project.token;

import cn.hutool.core.util.StrUtil;
import com.autuan.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * JWT 切面
 */
@Slf4j
@Aspect
@Component
public class MemberTokenAspect {

    /**
     * 配置织入点
     */
    @Pointcut("@annotation(com.autuan.project.token.MemberToken)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void doBefore(JoinPoint point) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        MemberToken myAnnotation = method.getAnnotation(MemberToken.class);
        boolean required = myAnnotation.required();
        if (required) {
            String mt = request.getHeader("MT");
            if(StrUtil.isBlank(mt)){
                throw new BusinessException(TokenErrorEnum.FAIL);
            }
            // todo token 存在验证（redis) [Autuan.Yu](12.31)
            // todo 逻辑删除
            // todo 服务结束时间
            // todo 机器码
        }

    }
}
