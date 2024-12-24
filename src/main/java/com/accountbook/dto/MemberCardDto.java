package com.accountbook.dto;

import java.util.ArrayList;
import java.util.List;

import com.accountbook.entity.Card;
import com.accountbook.entity.Member;
import com.accountbook.entity.MemberCard;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberCardDto {
	private Integer mcSeq;
	
    private Integer mbSeq;

    private String mbId;

    private String mbNick;
    
    private Integer cardSeq;
    
    private String cardSeqStr;

    private String cardNm;
    
    private String mcNick;
    
    public MemberCard setEntity() {
    	MemberCard entity = new MemberCard();
    	Member member = new Member();
    	Card card = new Card();
    	member.setMbSeq(mbSeq);
    	card.setCardSeq(Integer.parseInt(cardSeqStr));

    	entity.setMcSeq(mcSeq);
    	entity.setMember(member);
    	entity.setCard(card);
    	entity.setMcNick(mcNick);
    	
    	return entity;
    }
    
    public static List<MemberCard> setEntity(List<MemberCardDto> dtoList, Integer mbSeq) {
    	List<MemberCard> entityList = new ArrayList<>();
    	
    	for(MemberCardDto dto : dtoList) {
        	MemberCard entity = new MemberCard();
        	Member member = new Member();
        	Card card = new Card();
        	
        	if(dto.getMbSeq() != null) {
        		member.setMbSeq(dto.getMbSeq());
        	} else {
        		member.setMbSeq(mbSeq);
        	}
        	card.setCardSeq(Integer.parseInt(dto.getCardSeqStr()));

        	entity.setMcSeq(dto.getMcSeq());
        	entity.setMember(member);
        	entity.setCard(card);
        	entity.setMcNick(dto.getMcNick());
        	entityList.add(entity);
    	}
    	
    	return entityList;
    }
}
