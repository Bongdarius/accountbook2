package com.accountbook.service;

import java.util.List;

import com.accountbook.base.BaseService;
import com.accountbook.entity.Member;

public interface MemberService extends BaseService<Member>{
	public List<Member> initDatas() throws Exception;
	public Member login(Member member) throws Exception;
	public Member resetPw(String mbId) throws Exception;
}
