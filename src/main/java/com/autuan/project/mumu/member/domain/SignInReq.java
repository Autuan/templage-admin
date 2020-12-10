package com.autuan.project.mumu.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * 登录请求对象 
 * @author Autuan.Yu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInReq {
    /**
     * 用户账号
     */
    @NotEmpty(message = "用户账号不能为空")
    private String no;
    /**
     * 用户密码
     */
    @NotEmpty(message = "密码不能为空")
    private String password;
}
