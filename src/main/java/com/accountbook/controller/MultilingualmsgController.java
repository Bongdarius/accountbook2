package com.accountbook.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountbook.dto.MultilingualmsgDto;
import com.accountbook.service.MultilingualmsgService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/language")
@RequiredArgsConstructor
public class MultilingualmsgController {

	private final MultilingualmsgService service;
	
	@GetMapping
	public ResponseEntity<List<MultilingualmsgDto>> selectList(@RequestBody MultilingualmsgDto dto) throws Exception {

		List<MultilingualmsgDto> list = service.selectList(dto);
		return ResponseEntity.ok(list);
	} 
}