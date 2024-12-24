package com.accountbook.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountbook.dto.MemberDto;
import com.accountbook.dto.PurchaseDto;
import com.accountbook.dto.PurchaseMonthDto;
import com.accountbook.entity.Member;
import com.accountbook.entity.Purchase;
import com.accountbook.service.PurchaseService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/purchase")
@RequiredArgsConstructor
public class PurchaseController {
	
	private final PurchaseService service;
	
	@GetMapping
	public ResponseEntity<List<PurchaseDto>> selectList(HttpSession session, @Param(value = "month") Integer month) throws Exception {
		MemberDto memberDto = (MemberDto)session.getAttribute("loginMember");
		
		Purchase purchase = new Purchase();
		Member member = new Member();
		member.setMbSeq(memberDto.getMbSeq());
		purchase.setMember(member);
		
		List<PurchaseDto> dtoList = new ArrayList<>();
		
		if(month == null) {
			service.selectList(purchase).forEach(each -> dtoList.add(each.setDto()));
		} else {
			service.selectListByMonth(purchase, month).forEach(each -> dtoList.add(each.setDto()));
		}
		
		return Optional.ofNullable(dtoList)
				.map(dtolist_ -> ResponseEntity.ok(dtolist_))
				.orElse(ResponseEntity.noContent().build());
	}
	
	@PostMapping(value = "/list")
	public ResponseEntity<List<PurchaseDto>> saveList(@RequestBody List<PurchaseDto> paramDtoList) throws Exception {
//		try {
//			System.out.println(paramDtoList.toString());
//			return null;
			List<Purchase> purchaseList = new ArrayList<>();
			paramDtoList.forEach(each -> purchaseList.add(each.setEntity()));
			
			System.out.println(purchaseList.toString());
			
			List<PurchaseDto> dtoList = new ArrayList<>();
			service.saveList(purchaseList).forEach(each -> dtoList.add(each.setDto()));
			
			ResponseEntity<List<PurchaseDto>> response = Optional.ofNullable(dtoList)
															.map(memberCardList_ -> ResponseEntity.ok(memberCardList_))
															.orElse(ResponseEntity.noContent().build());
		return response;
//		} catch(Exception e) {
//			if(e.getMessage().contains("duplicate key value violates unique constraint")) {
//				throw new Exception("중복");
//			} else {
//				throw new Exception("수정중 오류 발생");
//			}
//		}
	}	
	
	@PostMapping(value = "/deleteList")
	public void deleteList(@RequestBody List<PurchaseDto> paramDtoList) throws Exception {
		try {
			List<Purchase> purchaseList = new ArrayList<>();
			paramDtoList.forEach(each -> purchaseList.add(each.setEntity()));
			service.deleteList(purchaseList);
		} catch(Exception e) {
			if(e.getMessage().contains("duplicate key value violates unique constraint")) {
				throw new Exception("중복");
			} else {
				throw new Exception("삭제중 오류 발생");
			}
		}
	}
	
	@GetMapping(value = "/month")
	public ResponseEntity<List<PurchaseMonthDto>> selectPurchaseMonth(@Param(value = "mbSeq") Integer mbSeq) throws Exception {
		List<PurchaseMonthDto> purchaseMonthList = service.selectPurchaseMonth(mbSeq);
		
		ResponseEntity<List<PurchaseMonthDto>> response = Optional.ofNullable(purchaseMonthList)
															.map(purchaseMonthList_ -> ResponseEntity.ok(purchaseMonthList_))
															.orElse(ResponseEntity.noContent().build());
		
		return response;
	}
}
