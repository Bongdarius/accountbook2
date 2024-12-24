package com.accountbook.service;

import java.util.List;

import com.accountbook.base.BaseService;
import com.accountbook.dto.ItemDto;
import com.accountbook.entity.Card;
import com.accountbook.entity.Member;
import com.accountbook.entity.MemberCard;

public interface MemberCardService extends BaseService<MemberCard>{
	public List<MemberCard> initDatas() throws Exception;

	public MemberCard selectOne(Member member, Card card);

	public List<ItemDto> selectListByItems(Member member);
}
