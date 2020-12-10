package com.autuan.project.mumu.member.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.autuan.common.exception.BusinessException;
import com.autuan.common.utils.Md5Utils;
import com.autuan.project.mumu.member.domain.SignInReq;
import com.autuan.project.mumu.member.domain.TabMember;
import com.autuan.project.mumu.member.domain.TabMemberExample;
import com.autuan.project.mumu.member.mapper.MemberMapper;
import com.autuan.project.mumu.member.mapper.TabMemberMapper;
import com.autuan.project.mumu.member.service.IMemberCustomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
@Service
@Slf4j
public class MemberCustomServiceImpl implements IMemberCustomService {
    @Autowired
    private TabMemberMapper tabMemberMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String signIn(SignInReq req) {
        String hashPwd = Md5Utils.hash(req.getPassword());
        log.info("MemberCustomServiceImpl -> signIn -> no -> {} hashPwd -> {}",req.getNo(),hashPwd);
        TabMemberExample example = new TabMemberExample();
        example.createCriteria()
                // todo magic val 2-删除 [Autuan.Yu](20.12.31)
                .andDelFlagNotEqualTo(2)
                .andNoEqualTo(req.getNo())
                .andPasswordEqualTo(hashPwd);
        TabMember member = tabMemberMapper.selectOneByExample(example);
        if(null == member ) {
            throw new BusinessException("已停用");
        }

        return generatorToken(member);
    }

    @Override
    public String generatorToken(TabMember member) {
        String token = IdUtil.simpleUUID();
        redisTemplate.opsForHash().put(member.getId(),token,JSONUtil.toJsonStr(member));
        redisTemplate.opsForValue().set(token, JSONUtil.toJsonStr(member),30, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(member.getId(), JSONUtil.toJsonStr(member),30, TimeUnit.MINUTES);

        return token;
    }
}
