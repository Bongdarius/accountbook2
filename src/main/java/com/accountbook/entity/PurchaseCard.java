package com.accountbook.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.accountbook.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "purchase_card")
public class PurchaseCard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_card_seq_generator")
    @SequenceGenerator(name = "purchase_card_seq_generator", sequenceName = "purchase_card_seq", allocationSize = 1)
    @ColumnDefault("nextval('purchase_card_seq'::regclass)")
    private Integer pccSeq;
    
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "mc_seq", nullable = false)
    private MemberCard memberCard;
}
