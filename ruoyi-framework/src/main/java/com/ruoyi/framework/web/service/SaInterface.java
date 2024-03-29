package com.ruoyi.framework.web.service;

import cn.dev33.satoken.stp.StpInterface;
import com.ruoyi.common.core.domain.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @author: wangming
 * @date: 2021年06月09日 22:38
 */
/**
* @Description: TODO
* @author: wangming
* @date: 作为权限处理认证

* @Return: 
*/
@Component
public class SaInterface implements StpInterface {
    @Autowired
    private TokenService tokenService;

    @Override
    public List<String> getPermissionList(Object o, String s) {
       LoginUser loginUser=tokenService.getLoginUser();
       //采用的是用户里自带的权限，实现一次性访问reids,进行判断是否可以访问
       return loginUser.getPermissions().stream().collect(Collectors.toList());
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        return null;
    }
}
