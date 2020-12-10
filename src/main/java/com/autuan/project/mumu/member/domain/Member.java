package com.autuan.project.mumu.member.domain;

import com.autuan.framework.aspectj.lang.annotation.Excel;
import com.autuan.framework.web.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 会员信息对象 tab_member
 * 
 * @author autuan
 * @date 2020-11-29
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;
    /** 编号 */
    @Excel(name = "编号")
    private String no;
    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;
    /** email */
    @Excel(name = "email")
    private String email;
    /** 密码 */
    @Excel(name = "密码")
    private String password;
    /** 安全问题 */
    @Excel(name = "安全问题")
    private String safeQuestion;
    /** 安全问题答案 */
    @Excel(name = "安全问题答案")
    private String safeAnswere;
    /** 层级 0：最低 1：1级 2：2级 */
    @Excel(name = "层级 0：最低 1：1级 2：2级")
    private Integer level;
    /** vip等级 0-普通 */
    @Excel(name = "vip等级 0-普通")
    private Integer vip;
    /** vip过期时间：超过过期时间，vip等级降为0 */
    @Excel(name = "vip过期时间：超过过期时间，vip等级降为0")
    private Long vipExpireTime;
    /** 服务时间 */
    @Excel(name = "服务时间")
    private Long startTime;
    /** 服务结束时间 */
    @Excel(name = "服务结束时间")
    private Long endTime;
    /** 机器码 */
    @Excel(name = "机器码")
    private String machineCode;
    /** 白名单IP */
    @Excel(name = "白名单IP")
    private String whiteIp;


}
