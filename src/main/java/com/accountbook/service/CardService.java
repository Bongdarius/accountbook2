package com.accountbook.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.accountbook.base.BaseService;
import com.accountbook.dto.ItemDto;
import com.accountbook.entity.Card;

public interface CardService extends BaseService<Card>{

	public List<Card> initDatas() throws Exception;
	public List<ItemDto> selectListByItems() throws Exception;

}
