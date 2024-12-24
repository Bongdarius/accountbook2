package com.accountbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accountbook.entity.Multilingualmsg;

public interface MultilingualmsgRepository extends JpaRepository<Multilingualmsg, Integer> {
	List<Multilingualmsg> findByOrderByMtmSeqAsc();
}
