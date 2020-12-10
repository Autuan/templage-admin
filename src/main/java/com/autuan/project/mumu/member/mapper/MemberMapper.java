package com.autuan.project.mumu.member.mapper;

import com.autuan.project.mumu.member.domain.Member;
import java.util.List;

/**
 * 会员信息Mapper接口
 * 
 * @author autuan
 * @date 2020-11-29
 */
public interface MemberMapper 
{
    /**
     * 查询会员信息
     * 
     * @param id 会员信息ID
     * @return 会员信息
     */
    public Member selectMemberById(String id);

    /**
     * 查询会员信息列表
     * 
     * @param member 会员信息
     * @return 会员信息集合
     */
    public List<Member> selectMemberList(Member member);

    /**
     * 新增会员信息
     * 
     * @param member 会员信息
     * @return 结果
     */
    public int insertMember(Member member);

    /**
     * 修改会员信息
     * 
     * @param member 会员信息
     * @return 结果
     */
    public int updateMember(Member member);

    /**
     * 删除会员信息
     * 
     * @param id 会员信息ID
     * @return 结果
     */
    public int deleteMemberById(String id);

    /**
     * 批量删除会员信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMemberByIds(String[] ids);
}
