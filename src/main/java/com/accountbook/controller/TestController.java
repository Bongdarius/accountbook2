package com.accountbook.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountbook.dto.TestDto;
import com.accountbook.entity.Test;
import com.accountbook.service.TestService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping(value = "/test")
@RequiredArgsConstructor
public class TestController {
	
	private final TestService service;
	
	@GetMapping
	public ResponseEntity<List<TestDto>> selectList() {
		List<TestDto> dtoList = new ArrayList<>();
		service.selectList(new Test()).forEach(test -> dtoList.add(test.setDto()));
		return ResponseEntity.ok(dtoList); 
	}
	
	@PostMapping(value = "/initDatas")
	public ResponseEntity<List<Test>> initDatas() throws Exception {
		return ResponseEntity.ok(service.initDatas());
	}
}
