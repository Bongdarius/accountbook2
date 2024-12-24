package com.accountbook.entity;

import com.accountbook.base.BaseEntity;
import com.accountbook.dto.TestDto;

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
@Table(name = "test")
public class Test extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer testSeq;

    @Column(name = "test_name", length = 50, nullable = false, unique = true)
    private String testNm;
    
    public TestDto setDto() {
    	TestDto dto = new TestDto();
    	dto.setTestSeq(testSeq);
    	dto.setTestNm(testNm);
    	return dto;
    }
}
