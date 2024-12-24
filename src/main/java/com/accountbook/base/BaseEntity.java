package com.accountbook.base;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@ToString
@EntityListeners(value = {AuditingEntityListener.class})
public class BaseEntity {
	
	@Column(nullable = false, updatable = false)
	@CreatedBy
	@Getter
	private Integer regUserId;
	
	@Column(nullable = false, updatable = false)
	@CreatedDate
	@Getter
	private LocalDateTime regDt;
	
	@Column(nullable = true)
	@LastModifiedBy
	@Getter
	@Setter
	private Integer modUserId;
	
	@Column(nullable = true)
	@LastModifiedDate
	@Getter
	@Setter
	private LocalDateTime modDt;
}
