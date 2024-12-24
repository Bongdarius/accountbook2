package com.accountbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accountbook.entity.Member;
import java.util.List;



public interface MemberRepository extends JpaRepository<Member, Integer> {
	Member findByMbId(String mbId);
	List<Member> findByOrderByMbSeqAsc();
}
