package com.accountbook.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.accountbook.dto.ItemDto;
import com.accountbook.entity.Card;
import com.accountbook.entity.Member;
import com.accountbook.entity.MemberCard;
import com.accountbook.repository.CardRepository;
import com.accountbook.repository.MemberCardRepository;
import com.accountbook.repository.MemberRepository;
import com.accountbook.service.MemberCardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberCardServiceImpl implements MemberCardService {

	private final MemberCardRepository repository;
	private final MemberRepository memberRepository; 
	private final CardRepository cardRepository; 
	
	@Override
	public MemberCard selectOne(MemberCard entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberCard> selectList(MemberCard entity) {
		return repository.findByMemberOrderByMcSeqAsc(entity.getMember());
	}

	@Override
	public MemberCard insertOne(MemberCard entity) {
		return repository.save(entity);
	}

	@Override
	public List<MemberCard> insertList(List<MemberCard> entityList) {
		List<MemberCard> memberCardList = new ArrayList<>();
		
		entityList.forEach(entity -> memberCardList.add(repository.save(entity)));
		
		return memberCardList;
	}

	@Override
	public MemberCard updateOne(MemberCard entity) {
		return repository.save(entity);
	}

	@Override
	public List<MemberCard> updateList(List<MemberCard> entityList) {
		List<MemberCard> memberCardList = new ArrayList<>();
		
		entityList.forEach(entity -> memberCardList.add(repository.save(entity)));
		
		return memberCardList;
	}

	@Override
	public void deleteOne(MemberCard entity) {
		repository.deleteById(entity.getMcSeq());
	}

	@Override
	public void deleteList(List<MemberCard> entityList) {
		entityList.forEach(entity -> repository.deleteById(entity.getMcSeq()));
	}

	@Override
	public List<MemberCard> initDatas() throws Exception {
		List<MemberCard> memberCardList = new ArrayList<>();
		
		List<Card> cardList = cardRepository.findAll();
		Member member = memberRepository.findByMbId("admin");
		
		for(Card card : cardList) {
			MemberCard memberCard = new MemberCard();
			memberCard.setCard(card);
			memberCard.setMember(member);
			memberCardList.add(repository.save(memberCard));
		}
		
		return memberCardList;
	}

	@Override
	public MemberCard selectOne(Member member, Card card) {
		return repository.findByMemberAndCard(member, card);
	}

	@Override
	public List<MemberCard> saveList(List<MemberCard> entityList) {
		List<MemberCard> memberCardList = new ArrayList<>();
		
		entityList.forEach(entity -> memberCardList.add(repository.save(entity)));
		
		return memberCardList;
	}

	@Override
	public List<ItemDto> selectListByItems(Member member) {
		List<ItemDto> dtoList = new ArrayList<>();
		repository.findByMemberOrderByMcSeqAsc(member).forEach(each -> dtoList.add(each.setItemDto()));
		return dtoList;
	}
}
