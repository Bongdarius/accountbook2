package com.accountbook.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.accountbook.dto.ItemDto;
import com.accountbook.dto.MemberCardDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "member_card")
public class MemberCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mc_seq_generator")
    @SequenceGenerator(name = "mc_seq_generator", sequenceName = "mc_seq", allocationSize = 1)
    @ColumnDefault("nextval('mc_seq'::regclass)")
    private Integer mcSeq;
    
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "mb_seq", nullable = false)
    private Member member;
    
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "card_seq", nullable = false)
    private Card card; 
    
    @OneToMany(mappedBy = "memberCard")
    private List<PurchaseCard> purchaseCardList;
    
    @Column(name = "mc_nick", length = 50, nullable = false)
    private String mcNick;
    
    public MemberCardDto setDto() {
    	MemberCardDto dto = new MemberCardDto();
    	dto.setMcSeq(mcSeq);
    	dto.setMbSeq(member.getMbSeq());
    	dto.setMbId(member.getMbId());
    	dto.setMbNick(member.getMbNick());
    	dto.setCardSeq(card.getCardSeq());
    	dto.setCardSeqStr(card.getCardSeq().toString());
    	dto.setCardNm(card.getCardNm());
    	dto.setMcNick(mcNick);
    	return dto;
    }
    
    public static List<MemberCardDto> setDto(List<MemberCard> memberCardList) {
    	List<MemberCardDto> dtoList = new ArrayList<>();
    	
    	for(MemberCard memberCard : memberCardList) {
    		MemberCardDto dto = new MemberCardDto();
    		dto.setMcSeq(memberCard.getMcSeq());
    		dto.setMbSeq(memberCard.getMember().getMbSeq());
    		dto.setMbId(memberCard.getMember().getMbId());
    		dto.setMbNick(memberCard.getMember().getMbNick());
    		dto.setCardSeq(memberCard.getCard().getCardSeq());
    		dto.setCardSeqStr(memberCard.getCard().getCardSeq().toString());
    		dto.setCardNm(memberCard.getCard().getCardNm());
    		dto.setMcNick(memberCard.getMcNick());
    		dtoList.add(dto);
    	}

    	return dtoList;
    }
    
    public ItemDto setItemDto() {
    	ItemDto dto = new ItemDto();
    	dto.setValue(mcSeq.toString());
    	dto.setText(mcNick);
    	return dto;
    }
}
