package com.accountbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accountbook.entity.MemberCard;
import java.util.List;
import com.accountbook.entity.Member;
import com.accountbook.entity.Card;




public interface MemberCardRepository extends JpaRepository<MemberCard, Integer> {
	
	MemberCard findByMemberAndCard(Member member, Card card);
	List<MemberCard> findByMemberOrderByMcSeqAsc(Member member);
	List<MemberCard> findByOrderByMcSeqAsc();
}
