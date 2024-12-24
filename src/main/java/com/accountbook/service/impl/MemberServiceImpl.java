package com.accountbook.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.accountbook.entity.Member;
import com.accountbook.repository.MemberRepository;
import com.accountbook.service.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	private final MemberRepository repository;
	
	@Override
	public Member selectOne(Member entity) {
		return null;
	}

	@Override
	public List<Member> selectList(Member entity) {
		return repository.findByOrderByMbSeqAsc();
	}

	@Override
	public Member insertOne(Member member) {
		String password = member.getMbPassword() == null ? "1234" : member.getMbPassword();
		String encryptedPassword = passwordEncoder.encode(password);
		member.setMbPassword(encryptedPassword);
		return repository.save(member);
	}

	@Override
	public List<Member> insertList(List<Member> entityList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member updateOne(Member member) {
		Member baseMember = repository.findById(member.getMbSeq()).get();
		baseMember.setMbId(member.getMbId());
		baseMember.setMbNick(member.getMbNick());
		return repository.save(baseMember);
	}

	@Override
	public List<Member> updateList(List<Member> entityList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOne(Member entity) {
		repository.deleteById(entity.getMbSeq());
	}

	@Override
	public void deleteList(List<Member> entityList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Member> initDatas() throws Exception {
		List<Member> memberList = new ArrayList<>();
		Member member = new Member();
		
		String password = "1234";
		
		member.setMbId("admin");
		member.setMbPassword(passwordEncoder.encode(password));
		member.setMbNick("최고관리자");
		memberList.add(repository.save(member));
		
		return memberList;
	}

	@Override
	public Member login(Member member) throws Exception {
		String password = member.getMbPassword();
		
		Member resultMember = repository.findByMbId(member.getMbId());
		
		String encryptedPassword = resultMember.getMbPassword();
		
		boolean checkPw = passwordEncoder.matches(password, encryptedPassword);
		
		if(checkPw) {
			return resultMember;
		} else {
			return null;
		}
	}

	@Override
	public Member resetPw(String mbId) throws Exception {
		Member member = Optional.ofNullable(repository.findByMbId(mbId))
							.orElseThrow(() -> new Exception("아이디를 잘못 입력하였습니다."));
		
		member.setMbPassword(passwordEncoder.encode("1234"));
		return repository.save(member);
	}

	@Override
	public List<Member> saveList(List<Member> entityList) {
		// TODO Auto-generated method stub
		return null;
	}
}
