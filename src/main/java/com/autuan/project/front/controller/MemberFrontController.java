package com.autuan.project.front.controller;

import com.autuan.project.token.MemberToken;
import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.mumu.member.domain.SignInReq;
import com.autuan.project.mumu.member.service.IMemberCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/front/member")
public class MemberFrontController {
    @Autowired
    private IMemberCustomService memberCustomService;

    /**
     * 登录方法
     *
     * @param req
     * @return
     * @author Autuan.Yu
     */
    @PostMapping("/sign")
    public ReturnResult signIn(@RequestBody @Valid SignInReq req) {
        String token = memberCustomService.signIn(req);
        return ReturnResult.ok(token);
    }

    @PostMapping("/checkMemberPower")
    @MemberToken
    public ReturnResult checkMemberPower() {
        return ReturnResult.ok();
    }
}
