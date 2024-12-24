package com.accountbook.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountbook.dto.ItemDto;
import com.accountbook.dto.PurchaseMethodDto;
import com.accountbook.entity.PurchaseMethod;
import com.accountbook.service.PurchaseMethodService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/purchase/method")
@RequiredArgsConstructor
public class PurchaseMethodController {

	private final PurchaseMethodService service;
	
	@PostMapping(value = "/initDatas")
	public ResponseEntity<List<PurchaseMethod>> initDatas() throws Exception {
		try {
			return Optional.ofNullable(service.initDatas())
					.map(list -> ResponseEntity.ok(list))
					.orElse(ResponseEntity.noContent().build());
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping
	public ResponseEntity<List<PurchaseMethodDto>> selectList() throws Exception {
		List<PurchaseMethodDto> dtoList = new ArrayList<>();
		List<PurchaseMethod> purchaseMethodList = service.selectList(new PurchaseMethod());
		purchaseMethodList.forEach(pcm -> dtoList.add(pcm.setDto()));
		return ResponseEntity.ok(dtoList);
	}  
	
	@PostMapping
	public ResponseEntity<PurchaseMethod> insertOne(@RequestBody PurchaseMethod entity) throws Exception {
		try {
			PurchaseMethod returnEntity = service.insertOne(entity);
			ResponseEntity<PurchaseMethod> response = Optional.ofNullable(returnEntity)
													.map(entity_ -> ResponseEntity.ok(entity))
													.orElse(ResponseEntity.noContent().build());
			return response;
		} catch(Exception e) {
			if(e.getMessage().contains("duplicate key value violates unique constraint")) {
				throw new Exception("중복 오류");
			} else {
				throw new Exception("저장중 오류 발생");
			}
		}
	}
	
	@PutMapping
	public ResponseEntity<PurchaseMethod> updateOne(@RequestBody PurchaseMethod entity) throws Exception {
		try {
			PurchaseMethod returnEntity = service.updateOne(entity);
			ResponseEntity<PurchaseMethod> response = Optional.ofNullable(returnEntity)
													.map(entity_ -> ResponseEntity.ok(entity_))
													.orElse(ResponseEntity.noContent().build());
			return response;
		} catch(Exception e) {
			if(e.getMessage().contains("duplicate key value violates unique constraint")) {
				throw new Exception("중복 오류");
			} else {
				throw new Exception("저장중 오류 발생");
			}
		}
	}
	
	@DeleteMapping(value = "/{pcmSeq}")
	public void deleteOne(@PathVariable("pcmSeq") Integer pcmSeq) throws Exception {
		PurchaseMethod entity = new PurchaseMethod();
		entity.setPcmSeq(pcmSeq);
		service.deleteOne(entity);
	}
	
	@GetMapping(value = "/byItems")
	public ResponseEntity<List<ItemDto>> selectListByItems() throws Exception {
		return ResponseEntity.ok(service.selectListByItems());
	} 
}