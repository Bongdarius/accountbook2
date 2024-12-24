package com.accountbook.service;

import java.util.List;

import com.accountbook.base.BaseService;
import com.accountbook.entity.Member;
import com.accountbook.entity.Test;

public interface TestService extends BaseService<Test>{
	public List<Test> initDatas() throws Exception;
}
