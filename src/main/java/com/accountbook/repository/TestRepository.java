package com.accountbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accountbook.entity.Member;
import com.accountbook.entity.Test;


public interface TestRepository extends JpaRepository<Test, Integer> {
}
