package com.autuan.project.mumu.member.service;

import com.autuan.project.mumu.member.domain.SignInReq;
import com.autuan.project.mumu.member.domain.TabMember;

public interface IMemberCustomService {
    /**
     * 登录
     * @param req
     * @return token
     */
    String signIn(SignInReq req);

    /**
     * 生成Token
     * @param member
     * @return
     */
    String generatorToken(TabMember member);

}
