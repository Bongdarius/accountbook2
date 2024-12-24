package com.accountbook.entity;

import com.accountbook.base.BaseEntity;
import com.accountbook.dto.MultilingualmsgDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "multilingualmsg")
public class Multilingualmsg extends BaseEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer mtmSeq;

    @Column(name = "country_cd", nullable = false)
    private String countryCd;

    @Column(name = "msg_code", nullable = false)
    private String msgCode;

    @Column(name = "message", nullable = false)
    private String message;
    
    public MultilingualmsgDto setDto() {
    	MultilingualmsgDto dto = new MultilingualmsgDto();
    	dto.setMtmSeq(this.mtmSeq);
    	dto.setCountryCd(this.countryCd);
    	dto.setMsgCode(this.msgCode);
    	dto.setMessage(this.message);
    	
    	return dto;
    }
}