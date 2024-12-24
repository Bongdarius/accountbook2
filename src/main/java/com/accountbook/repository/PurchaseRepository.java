package com.accountbook.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.accountbook.dto.PurchaseMonthDto;
import com.accountbook.entity.Member;
import com.accountbook.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
	List<Purchase> findByMemberOrderByPcDatetimeDesc(Member member);
    
    @Query("SELECT new com.accountbook.dto.PurchaseMonthDto(EXTRACT(MONTH FROM p.pcDatetime) as pc_month, SUM(p.pcAmt) as pc_sum) " +
            "FROM Purchase p " +
            "WHERE p.member.mbSeq = :mbSeq " +
            "GROUP BY EXTRACT(MONTH FROM p.pcDatetime)" +
            "ORDER BY pc_month ASC")
    List<PurchaseMonthDto> findMonthlyPurchaseSummary(@Param("mbSeq") Integer mbSeq);
    
    List<Purchase> findByMemberAndPcDatetimeBetweenOrderByPcDatetimeDesc(Member member, LocalDateTime startDate, LocalDateTime endDate);
}
