package com.accountbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accountbook.entity.Card;
import com.accountbook.entity.PurchaseMethod;

public interface PurchaseMethodRepository extends JpaRepository<PurchaseMethod, Integer> {
	List<PurchaseMethod> findByOrderByPcmSortNoAsc();
}
