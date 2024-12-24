package com.accountbook.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.accountbook.base.BaseService;
import com.accountbook.dto.ItemDto;
import com.accountbook.entity.Card;
import com.accountbook.entity.PurchaseMethod;

public interface PurchaseMethodService extends BaseService<PurchaseMethod>{

	public List<PurchaseMethod> initDatas() throws Exception;

	public List<ItemDto> selectListByItems();
}
