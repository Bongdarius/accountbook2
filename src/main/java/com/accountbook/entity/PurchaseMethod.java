package com.accountbook.entity;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import com.accountbook.base.BaseEntity;
import com.accountbook.dto.ItemDto;
import com.accountbook.dto.PurchaseMethodDto;

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
@Table(name = "purchase_method")
public class PurchaseMethod extends BaseEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_method_seq_generator")
    @SequenceGenerator(name = "purchase_method_seq_generator", sequenceName = "purchase_method_seq", allocationSize = 1)
    @ColumnDefault("nextval('purchase_method_seq'::regclass)")
    private Integer pcmSeq;
    
    @OneToMany(mappedBy = "purchaseMethod")
    private List<Purchase> purchaseList;
    
    @Column(length = 20, nullable = false, unique = true)
    private String pcmNm;
    
    @Column(name = "pcm_sort_no")
    private Integer pcmSortNo;
    
    public PurchaseMethodDto setDto() {
    	PurchaseMethodDto dto = new PurchaseMethodDto();
    	dto.setPcmSeq(pcmSeq);
    	dto.setPcmNm(pcmNm);
    	dto.setPcmSortNo(pcmSortNo);
    	return dto;
    }
    
    public ItemDto setItemDto() {
    	ItemDto dto = new ItemDto();
    	dto.setValue(pcmSeq.toString());
    	dto.setText(pcmNm);
    	return dto;
    }
}
