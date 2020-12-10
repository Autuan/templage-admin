package com.autuan.project.mumu.member.service.impl;

import java.util.List;
import cn.hutool.core.util.IdUtil;
                                                        import java.time.LocalDateTime;
            import com.autuan.common.utils.security.ShiroUtils;
    import java.time.LocalDateTime;
            import com.autuan.common.utils.security.ShiroUtils;
    import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autuan.project.mumu.member.mapper.MemberMapper;
import com.autuan.project.mumu.member.domain.Member;
import com.autuan.project.mumu.member.service.IMemberService;
import com.autuan.common.utils.text.Convert;

/**
 * 会员信息Service业务层处理
 * 
 * @author autuan
 * @date 2020-11-29
 */
@Service
public class MemberServiceImpl implements IMemberService 
{
    @Autowired
    private MemberMapper memberMapper;

    /**
     * 查询会员信息
     * 
     * @param id 会员信息ID
     * @return 会员信息
     */
    @Override
    public Member selectMemberById(String id)
    {
        return memberMapper.selectMemberById(id);
    }

    /**
     * 查询会员信息列表
     * 
     * @param member 会员信息
     * @return 会员信息
     */
    @Override
    public List<Member> selectMemberList(Member member)
    {
        return memberMapper.selectMemberList(member);
    }

    /**
     * 新增会员信息
     * 
     * @param member 会员信息
     * @return 结果
     */
    @Override
    public int insertMember(Member member)
    {
                                                                member.setCreateTime(LocalDateTime.now());
                member.setCreateBy(ShiroUtils.getLoginName());
                    member.setId(IdUtil.simpleUUID());
        return memberMapper.insertMember(member);
    }

    /**
     * 修改会员信息
     * 
     * @param member 会员信息
     * @return 结果
     */
    @Override
    public int updateMember(Member member)
    {
                                                                member.setUpdateTime(LocalDateTime.now());
                        member.setUpdateBy(ShiroUtils.getLoginName());
            return memberMapper.updateMember(member);
    }

    /**
     * 删除会员信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMemberByIds(String ids)
    {
        return memberMapper.deleteMemberByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除会员信息信息
     * 
     * @param id 会员信息ID
     * @return 结果
     */
    @Override
    public int deleteMemberById(String id)
    {
        return memberMapper.deleteMemberById(id);
    }
}
