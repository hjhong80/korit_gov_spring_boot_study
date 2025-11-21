package com.korit.korit_gov_spring_boot_study.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostRespDto<T> {
    private String status;
    private String message;
    private T body;
}
