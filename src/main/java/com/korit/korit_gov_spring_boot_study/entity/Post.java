package com.korit.korit_gov_spring_boot_study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Integer postId;
    private String title;
    private String content;
}

/*
*
* 등록시 타이틀 중복 확인
*
* 조회
* 전체리스트 조회
* 단건조회 (id)
* keyword 조회 (title)
*
* */