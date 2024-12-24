package com.accountbook.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountbook.dto.CardDto;
import com.accountbook.dto.ItemDto;
import com.accountbook.dto.MemberCardDto;
import com.accountbook.dto.MemberDto;
import com.accountbook.entity.Card;
import com.accountbook.entity.Member;
import com.accountbook.entity.MemberCard;
import com.accountbook.service.MemberCardService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/memberCard")
@RequiredArgsConstructor
public class MemberCardController {
	
	private final MemberCardService service;
	
	@PostMapping("/initDatas")
	public ResponseEntity<List<MemberCard>> initDatas() throws Exception {
		
		ResponseEntity<List<MemberCard>> memberCardList = Optional.ofNullable(service.initDatas())
															.map(list -> ResponseEntity.ok(list))
															.orElse(ResponseEntity.noContent().build());
		return memberCardList;
	}
	
	@GetMapping("/{mbSeq}/{cardSeq}")
	public ResponseEntity<MemberCardDto> selectOne(@PathVariable Integer mbSeq, @PathVariable Integer cardSeq) throws Exception {
		Member member = new Member();
		Card card = new Card();
		
		member.setMbSeq(mbSeq);
		card.setCardSeq(cardSeq);
		
		MemberCardDto dto = service.selectOne(member, card).setDto();
		
		return Optional.ofNullable(dto)
				.map(list -> ResponseEntity.ok(list))
				.orElse(ResponseEntity.noContent().build());
	}
	
	@GetMapping
	public ResponseEntity<List<MemberCardDto>> selectList(HttpSession session) throws Exception {
		MemberDto memberDto = (MemberDto)session.getAttribute("loginMember");
		
		MemberCard memberCard = new MemberCard();
		Member member = new Member();
		member.setMbSeq(memberDto.getMbSeq());
		memberCard.setMember(member);
		
		List<MemberCard> list = service.selectList(memberCard);
		List<MemberCardDto> dtoList = new ArrayList<>();
		
		list.forEach(each -> dtoList.add(each.setDto()));
		
		return Optional.ofNullable(dtoList)
				.map(dtolist_ -> ResponseEntity.ok(dtolist_))
				.orElse(ResponseEntity.noContent().build());
	}
	
	@PostMapping
	public ResponseEntity<MemberCardDto> insertOne(@RequestBody MemberCardDto paramDto) throws Exception {
		paramDto.setMbSeq(1);
		try {
			MemberCardDto returnCard = service.insertOne(paramDto.setEntity()).setDto();
			ResponseEntity<MemberCardDto> response = Optional.ofNullable(returnCard)
													.map(member_ -> ResponseEntity.ok(member_))
													.orElse(ResponseEntity.noContent().build());
		return response;
		} catch(Exception e) {
			if(e.getMessage().contains("duplicate key value violates unique constraint")) {
				throw new Exception("중복");
			} else {
				throw new Exception("저장중 오류 발생");
			}
		}
	}	
	
	@PostMapping(value = "/list")
	public ResponseEntity<List<MemberCardDto>> insertList(@RequestBody List<MemberCardDto> paramDtoList) throws Exception {
		try {
			List<MemberCard> memberCardList = service.insertList(MemberCardDto.setEntity(paramDtoList, 1));
			List<MemberCardDto> dtoList = MemberCard.setDto(memberCardList);
			
			ResponseEntity<List<MemberCardDto>> response = Optional.ofNullable(dtoList)
													.map(memberCardList_ -> ResponseEntity.ok(memberCardList_))
													.orElse(ResponseEntity.noContent().build());
		return response;
		} catch(Exception e) {
			if(e.getMessage().contains("duplicate key value violates unique constraint")) {
				throw new Exception("중복");
			} else {
				throw new Exception("저장중 오류 발생");
			}
		}
	}		
	
	@PutMapping
	public ResponseEntity<MemberCardDto> updateOne(@RequestBody MemberCardDto paramDto) throws Exception {
		try {
			MemberCardDto dto = service.updateOne(paramDto.setEntity()).setDto();
			ResponseEntity<MemberCardDto> response = Optional.ofNullable(dto)
													.map(entity_ -> ResponseEntity.ok(entity_))
													.orElse(ResponseEntity.noContent().build());
		return response;
		} catch(Exception e) {
			if(e.getMessage().contains("duplicate key value violates unique constraint")) {
				throw new Exception("중복");
			} else {
				throw new Exception("수정중 오류 발생");
			}
		}
	}
	
	@PutMapping(value = "/list")
	public ResponseEntity<List<MemberCardDto>> updateList(@RequestBody List<MemberCardDto> paramDtoList) throws Exception {
		try {
			List<MemberCard> memberCardList = service.updateList(MemberCardDto.setEntity(paramDtoList, 1));
			List<MemberCardDto> dtoList = MemberCard.setDto(memberCardList);
			
			ResponseEntity<List<MemberCardDto>> response = Optional.ofNullable(dtoList)
													.map(memberCardList_ -> ResponseEntity.ok(memberCardList_))
													.orElse(ResponseEntity.noContent().build());
		return response;
		} catch(Exception e) {
			if(e.getMessage().contains("duplicate key value violates unique constraint")) {
				throw new Exception("중복");
			} else {
				throw new Exception("수정중 오류 발생");
			}
		}
	}
	
	@PatchMapping(value = "/list")
	public ResponseEntity<List<MemberCardDto>> saveList(@RequestBody List<MemberCardDto> paramDtoList, HttpSession session) throws Exception {
		MemberDto memberDto = (MemberDto)session.getAttribute("loginMember");
		try {
			List<MemberCard> memberCardList = service.saveList(MemberCardDto.setEntity(paramDtoList, memberDto.getMbSeq()));
			List<MemberCardDto> dtoList = MemberCard.setDto(memberCardList);
			
			ResponseEntity<List<MemberCardDto>> response = Optional.ofNullable(dtoList)
													.map(memberCardList_ -> ResponseEntity.ok(memberCardList_))
													.orElse(ResponseEntity.noContent().build());
		return response;
		} catch(Exception e) {
			if(e.getMessage().contains("duplicate key value violates unique constraint")) {
				throw new Exception("중복");
			} else {
				throw new Exception("수정중 오류 발생");
			}
		}
	}	
	
	@DeleteMapping(value = "/{mcSeq}")
	public void deleteOne(@PathVariable("mcSeq") Integer mcSeq) throws Exception {
		MemberCard entity = new MemberCard();
		entity.setMcSeq(mcSeq);
		service.deleteOne(entity);
	}
	
	@PostMapping(value = "/deleteList")
	public void deleteList(@RequestBody List<MemberCardDto> paramDtoList, HttpSession session) throws Exception {
		MemberDto memberDto = (MemberDto)session.getAttribute("loginMember");
		try {
			service.deleteList(MemberCardDto.setEntity(paramDtoList, memberDto.getMbSeq()));
		} catch(Exception e) {
			if(e.getMessage().contains("duplicate key value violates unique constraint")) {
				throw new Exception("중복");
			} else {
				throw new Exception("삭제중 오류 발생");
			}
		}
	}
	
	@GetMapping(value = "/byItems")
	public ResponseEntity<List<ItemDto>> selectListByItems(HttpSession session) throws Exception {
		MemberDto memberDto = (MemberDto)session.getAttribute("loginMember");
		Member member = new Member();
		member.setMbSeq(memberDto.getMbSeq());
		return ResponseEntity.ok(service.selectListByItems(member));
	} 
}
