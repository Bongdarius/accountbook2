package com.accountbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accountbook.entity.Card;
import com.accountbook.entity.PurchaseCard;
import com.accountbook.entity.PurchaseMethod;

public interface PurchaseCardRepository extends JpaRepository<PurchaseCard, Integer> {
	List<PurchaseCard> findByOrderByPccSeqAsc();
}
