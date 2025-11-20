package com.korit.korit_gov_spring_boot_study.dto;

import com.korit.korit_gov_spring_boot_study.entity.Member;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddMemberReqDto {
    private String name;
    private int age;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .age(age)
                .build();
    }
}
