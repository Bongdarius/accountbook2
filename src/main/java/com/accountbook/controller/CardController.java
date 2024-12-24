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

import com.accountbook.dto.CardDto;
import com.accountbook.dto.ItemDto;
import com.accountbook.entity.Card;
import com.accountbook.service.CardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/card")
@RequiredArgsConstructor
public class CardController {

	private final CardService service;
	
	@PostMapping(value = "/initDatas")
	public ResponseEntity<List<Card>> initDatas() throws Exception {
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
	public ResponseEntity<List<CardDto>> selectList() throws Exception {
		List<Card> cardList = service.selectList(new Card());
		List<CardDto> dtoList = new ArrayList<>();
		cardList.forEach(card -> dtoList.add(card.setDto()));
		
		return ResponseEntity.ok(dtoList);
	} 
	
	@GetMapping(value = "/byItems")
	public ResponseEntity<List<ItemDto>> selectListByItems() throws Exception {
		return ResponseEntity.ok(service.selectListByItems());
	} 	
	
	@PostMapping
	public ResponseEntity<CardDto> insertOne(@RequestBody Card card) throws Exception {
		try {
			Card returnCard = service.insertOne(card);
			ResponseEntity<CardDto> returnCardDto = Optional.ofNullable(returnCard.setDto())
													.map(member_ -> ResponseEntity.ok(member_))
													.orElse(ResponseEntity.noContent().build());
			return returnCardDto;
		} catch(Exception e) {
			if(e.getMessage().contains("duplicate key value violates unique constraint")) {
				throw new Exception("아이디 중복");
			} else {
				throw new Exception("회원가입 중 오류 발생");
			}
		}
	}
	
	@PutMapping
	public ResponseEntity<CardDto> updateOne(@RequestBody Card card) throws Exception {
		try {
			Card returnEntity = service.updateOne(card);
			ResponseEntity<CardDto> returnDto = Optional.ofNullable(returnEntity.setDto())
													.map(card_ -> ResponseEntity.ok(card_))
													.orElse(ResponseEntity.noContent().build());
			return returnDto;
		} catch(Exception e) {
			if(e.getMessage().contains("duplicate key value violates unique constraint")) {
				throw new Exception("카드명 중복");
			} else {
				throw new Exception("카드정보 수정중 오류 발생");
			}
		}
	}
	
	@DeleteMapping(value = "/{cardSeq}")
	public void deleteOne(@PathVariable("cardSeq") Integer cardSeq) throws Exception {
		Card card = new Card();
		card.setCardSeq(cardSeq);
		service.deleteOne(card);
	}
}