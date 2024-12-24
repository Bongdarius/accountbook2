package com.accountbook.service;

import java.util.List;

import com.accountbook.dto.MultilingualmsgDto;

public interface MultilingualmsgService {
	public List<MultilingualmsgDto> selectList(MultilingualmsgDto dto) throws Exception;
}
