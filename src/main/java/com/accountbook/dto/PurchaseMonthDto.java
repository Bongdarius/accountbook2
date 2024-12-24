package com.accountbook.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PurchaseMonthDto {
	
	private Integer pcMonth;
    private BigDecimal pcSum;
	
	public PurchaseMonthDto(Integer pcMonth, BigDecimal pcSum) {
		this.pcMonth = pcMonth;
		this.pcSum = pcSum;
	}
}
