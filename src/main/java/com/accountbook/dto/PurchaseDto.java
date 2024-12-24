package com.accountbook.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.accountbook.entity.Member;
import com.accountbook.entity.MemberCard;
import com.accountbook.entity.Purchase;
import com.accountbook.entity.PurchaseCard;
import com.accountbook.entity.PurchaseMethod;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PurchaseDto {
	
	private Integer pcSeq;
	
	private Integer mbSeq;
	
	private Integer pcmSeq;
	
	private String pcmNm;
	
	private Integer pccSeq;
	
	private Integer mcSeq;
	
	private String mcNick;
	
	private BigDecimal pcAmt;

	private String pcDatetime;
	
	private String pcRemark;
	
	public Purchase setEntity() {
		Purchase entity = new Purchase();
		Member member = new Member();
		PurchaseCard purchaseCard = new PurchaseCard();
		MemberCard memberCard = new MemberCard();
		PurchaseMethod method = new PurchaseMethod();
		
		entity.setPcSeq(pcSeq);
		
		member.setMbSeq(mbSeq);
		entity.setMember(member);
		
		method.setPcmSeq(pcmSeq);
		method.setPcmNm(pcmNm);
		entity.setPurchaseMethod(method);
		
		if(pcmSeq == 1) {
			purchaseCard.setPccSeq(pccSeq);
			memberCard.setMcSeq(mcSeq);
			memberCard.setMcNick(mcNick);
			purchaseCard.setMemberCard(memberCard);
			entity.setPurchaseCard(purchaseCard);
		}

		entity.setPcAmt(pcAmt);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(pcDatetime, formatter);
		entity.setPcDatetime(dateTime);
		
		entity.setPcRemark(pcRemark);
		
		return entity;
	}
}
