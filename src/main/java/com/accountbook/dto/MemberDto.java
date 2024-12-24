package com.accountbook.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDto {

    private Integer mbSeq;

    private String mbId;

    private String mbNick;
}
