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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accountbook.dto.MemberDto;
import com.accountbook.entity.Member;
import com.accountbook.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService service;
	
	@PostMapping("/initDatas")
	public ResponseEntity<List<Member>> initDatas() throws Exception {
		return Optional.ofNullable(service.initDatas())
				.map(list -> ResponseEntity.ok(list))
				.orElse(ResponseEntity.noContent().build());
	}
	
	@GetMapping
	public ResponseEntity<List<MemberDto>> selectList() throws Exception {
		List<MemberDto> dto = new ArrayList<>(); 
		List<Member> memberList = service.selectList(new Member());
		
		try {
			for(Member member : memberList) {
				dto.add(member.setDto());
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(memberList.size());
		}
	
		
		ResponseEntity<List<MemberDto>> returnMemberList = ResponseEntity.ok(dto);
		return returnMemberList;
	}
	
	@GetMapping("/login")
	public ResponseEntity<MemberDto> login(@RequestParam String mbId, @RequestParam String mbPassword, HttpSession session) throws Exception {
		Member member = new Member();
		member.setMbId(mbId);
		member.setMbPassword(mbPassword);
		MemberDto dto = service.login(member).setDto();
		
		session.setAttribute("loginMember", dto);
		
		ResponseEntity<MemberDto> returnMember = Optional.ofNullable(dto)
												.map(member_ -> ResponseEntity.ok(member_))
												.orElse(ResponseEntity.noContent().build());
		return returnMember;
	}
	
	@GetMapping("/resetPw")
	public boolean resetPw(@RequestParam String mbId) throws Exception {
		try {
			Member member = service.resetPw(mbId);
			if(member == null) throw new Exception();
			return true;
		} catch(Exception e) {
			throw new Exception("오류 발생");
		}
	}
	
	@PostMapping
	public ResponseEntity<MemberDto> insertOne(@RequestBody Member member) throws Exception {
		try {
		Member returnMember = service.insertOne(member);
		ResponseEntity<MemberDto> returnMemberDto = Optional.ofNullable(returnMember.setDto())
												.map(member_ -> ResponseEntity.ok(member_))
												.orElse(ResponseEntity.noContent().build());
		return returnMemberDto;
		} catch(Exception e) {
			if(e.getMessage().contains("duplicate key value violates unique constraint")) {
				throw new Exception("아이디 중복");
			} else {
				throw new Exception("회원가입 중 오류 발생");
			}
		}
	}
	
	@PutMapping
	public ResponseEntity<MemberDto> updateOne(@RequestBody Member member) throws Exception {
		try {
		Member returnMember = service.updateOne(member);
		ResponseEntity<MemberDto> returnMemberDto = Optional.ofNullable(returnMember.setDto())
												.map(member_ -> ResponseEntity.ok(member_))
												.orElse(ResponseEntity.noContent().build());
		return returnMemberDto;
		} catch(Exception e) {
			if(e.getMessage().contains("duplicate key value violates unique constraint")) {
				throw new Exception("아이디 중복");
			} else {
				throw new Exception("회원정보 수정중 오류 발생");
			}
		}
	}
	
	@DeleteMapping(value = "/{mbSeq}")
	public void deleteOne(@PathVariable("mbSeq") Integer mbSeq) throws Exception {
		Member member = new Member();
		member.setMbSeq(mbSeq);
		service.deleteOne(member);
	}
	
	@GetMapping(value = "/isLogin")
	public ResponseEntity<MemberDto> isLogin(HttpSession session) {
		MemberDto dto = (MemberDto) session.getAttribute("loginMember");
		if(dto == null) return null; 
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping(value = "/logout")
	public void logout(HttpSession session) {
		session.removeAttribute("loginMember");
	}
}
