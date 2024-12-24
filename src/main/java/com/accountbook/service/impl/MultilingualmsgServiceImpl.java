package com.accountbook.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.accountbook.dto.MultilingualmsgDto;
import com.accountbook.entity.Multilingualmsg;
import com.accountbook.entity.QMultilingualmsg;
import com.accountbook.service.MultilingualmsgService;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MultilingualmsgServiceImpl implements MultilingualmsgService {

//	private final MultilingualmsgRepository repository;
	
	private final JPAQueryFactory queryFactory;

	@Override
	public List<MultilingualmsgDto> selectList(MultilingualmsgDto dto) throws Exception {
		QMultilingualmsg multilingualmsg = QMultilingualmsg.multilingualmsg;
		
		List<Multilingualmsg> list = queryFactory.selectFrom(multilingualmsg)
													.where(multilingualmsg.countryCd.eq(dto.getCountryCd()))
													.fetch();
		
		List<MultilingualmsgDto> dtoList = new ArrayList<>();

		list.forEach(entity -> dtoList.add(entity.setDto()));
		
		return dtoList;
	}
}
