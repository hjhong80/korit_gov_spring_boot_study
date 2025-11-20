package com.korit.korit_gov_spring_boot_study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    private Integer userId;
    private String name;
    private int age;
}
