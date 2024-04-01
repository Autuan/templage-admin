package com.ruoyi.util;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.web.service.TokenService;

/**
 * 安全服务工具类
 * 
 * @author ruoyi
 */
public class SecurityUtils
{
    /**
     * 获取用户账户
     **/
    public static String getUsername()
    {
        try
        {
            return getLoginUser().getUsername();
        }
        catch (Exception e)
        {
            throw new CustomException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser()
    {
//         Long userId = StpUtil.getLoginIdAsLong();
//         LoginUser user = redisCache.getCacheObject("admin:login:user:" + userId);
         TokenService tokenService= SpringUtils.getBean(TokenService.class);
        SpringUtil.getBean(TokenService.class);
        try
        {
            return  tokenService.getLoginUser();
//            return null;
        }
        catch (Exception e)
        {
            throw new CustomException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
        }
//            throw new CustomException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password)
    {
       return SaSecureUtil.sha256(password);
    }

    public static void main(String[] args){
        System.out.println(encryptPassword("1111"));
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword 真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword)
    {
        String encryptedPassword=encryptPassword(rawPassword);
        return StringUtils.equals(encryptedPassword,encodedPassword);
    }

    /**
     * 是否为管理员
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }
}
