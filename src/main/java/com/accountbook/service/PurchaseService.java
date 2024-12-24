package com.accountbook.service;

import java.util.List;

import com.accountbook.base.BaseService;
import com.accountbook.dto.PurchaseDto;
import com.accountbook.dto.PurchaseMonthDto;
import com.accountbook.entity.Purchase;

public interface PurchaseService extends BaseService<Purchase>{
	public List<PurchaseMonthDto> selectPurchaseMonth(Integer mbSeq) throws Exception;

	public List<Purchase> selectListByMonth(Purchase purchase, Integer month);
}
