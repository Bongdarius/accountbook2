package com.accountbook.entity;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import com.accountbook.base.BaseEntity;
import com.accountbook.dto.CardDto;
import com.accountbook.dto.ItemDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "card")
public class Card extends BaseEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_seq_generator")
    @SequenceGenerator(name = "card_seq_generator", sequenceName = "card_seq", allocationSize = 1)
    @ColumnDefault("nextval('card_seq'::regclass)")
    private Integer cardSeq;
    
    @Column(name = "card_nm", unique = true, nullable = false)
    private String cardNm;
    
    @OneToMany(mappedBy = "card")
    private List<MemberCard> memberCardList;
    
    @Column(name = "card_sort_no")
    private Integer cardSortNo;
    
    public CardDto setDto() {
    	CardDto dto = new CardDto();
    	dto.setCardSeq(cardSeq);
    	dto.setCardNm(cardNm);
    	dto.setCardSortNo(cardSortNo);
    	return dto;
    }
    
    public ItemDto setItemDto() {
    	ItemDto dto = new ItemDto();
    	dto.setValue(cardSeq.toString());
    	dto.setText(cardNm);
    	return dto;
    }
}
