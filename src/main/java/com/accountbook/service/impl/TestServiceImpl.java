package com.accountbook.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.accountbook.entity.Test;
import com.accountbook.repository.TestRepository;
import com.accountbook.service.TestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
	
	private final TestRepository repository;

	@Override
	public Test selectOne(Test entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Test> selectList(Test entity) {
		return repository.findAll();
	}

	@Override
	public Test insertOne(Test entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Test> insertList(List<Test> entityList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Test updateOne(Test entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Test> updateList(List<Test> entityList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOne(Test entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteList(List<Test> entityList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Test> initDatas() throws Exception {
		List<Test> testList = new ArrayList<>();
		for(int i = 0; i < 10000; i++) {
			Test test = new Test();
			test.setTestNm(String.format("테스트 %d", i+1));
			testList.add(repository.save(test));
		}
		return testList;
	}

	@Override
	public List<Test> saveList(List<Test> entityList) {
		// TODO Auto-generated method stub
		return null;
	}
}
