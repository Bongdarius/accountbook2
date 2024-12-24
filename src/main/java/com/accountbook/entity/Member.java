package com.accountbook.entity;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import com.accountbook.base.BaseEntity;
import com.accountbook.dto.MemberDto;

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
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    @SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq", allocationSize = 1)
    @ColumnDefault("nextval('member_seq'::regclass)")
    private Integer mbSeq;

    @OneToMany(mappedBy = "member")
    private List<Purchase> purchaseList;

    @OneToMany(mappedBy = "member")
    private List<MemberCard> memberCardList;
    
    @Column(name = "mb_id", length = 50, nullable = false, unique = true)
    private String mbId;

    @Column(name = "mb_password", length = 500, nullable = false)
    private String mbPassword;

    @Column(name = "mb_nick", length = 50, nullable = false)
    private String mbNick;
    
    public MemberDto setDto() {
    	MemberDto dto = new MemberDto();
    	dto.setMbSeq(this.mbSeq);
    	dto.setMbId(this.mbId);
    	dto.setMbNick(this.mbNick);
    	return dto;
    }
}
