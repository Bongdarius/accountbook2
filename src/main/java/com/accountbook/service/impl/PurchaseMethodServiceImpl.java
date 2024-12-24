package com.accountbook.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.accountbook.dto.ItemDto;
import com.accountbook.entity.PurchaseMethod;
import com.accountbook.repository.CardRepository;
import com.accountbook.repository.PurchaseMethodRepository;
import com.accountbook.service.CardService;
import com.accountbook.service.PurchaseMethodService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseMethodServiceImpl implements PurchaseMethodService {

	private final PurchaseMethodRepository repository;
		
	@Override
	public PurchaseMethod selectOne(PurchaseMethod PurchaseMethod) {
		return repository.findById(PurchaseMethod.getPcmSeq()).get();
	}

	@Override
	public List<PurchaseMethod> selectList(PurchaseMethod PurchaseMethod) {
		return repository.findByOrderByPcmSortNoAsc();
	}

	@Override
	public PurchaseMethod insertOne(PurchaseMethod PurchaseMethod) {
		return repository.save(PurchaseMethod);
	}

	@Override
	public List<PurchaseMethod> insertList(List<PurchaseMethod> entityList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PurchaseMethod updateOne(PurchaseMethod PurchaseMethod) {
		return repository.save(PurchaseMethod);
	}

	@Override
	public List<PurchaseMethod> updateList(List<PurchaseMethod> entityList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOne(PurchaseMethod PurchaseMethod) {
		repository.deleteById(PurchaseMethod.getPcmSeq());
	}

	@Override
	public void deleteList(List<PurchaseMethod> entityList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public List<PurchaseMethod> initDatas() throws Exception {
		List<PurchaseMethod> purchaseMethodList = new ArrayList<>();
		
		List<String> pcmNmList = new ArrayList<>();
		pcmNmList.add("카드");
		pcmNmList.add("현금");
		pcmNmList.add("상품권");
		pcmNmList.add("기타");
		
		for(String pcmNm : pcmNmList) {
			PurchaseMethod PurchaseMethod = new PurchaseMethod();
			PurchaseMethod.setPcmNm(pcmNm);
			repository.save(PurchaseMethod);
			purchaseMethodList.add(PurchaseMethod);
		}
		
		return purchaseMethodList;
	}

	@Override
	public List<PurchaseMethod> saveList(List<PurchaseMethod> entityList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemDto> selectListByItems() {
		List<ItemDto> dtoList = new ArrayList<>();
		repository.findByOrderByPcmSortNoAsc().forEach(each -> dtoList.add(each.setItemDto()));
		return dtoList;
	}

}
