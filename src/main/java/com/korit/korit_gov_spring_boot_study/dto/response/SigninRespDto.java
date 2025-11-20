package com.korit.korit_gov_spring_boot_study.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SigninRespDto {
    private String status;
    private String message;
}
