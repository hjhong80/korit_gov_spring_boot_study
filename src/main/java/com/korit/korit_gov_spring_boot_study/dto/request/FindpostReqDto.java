package com.korit.korit_gov_spring_boot_study.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindpostReqDto {
    private Integer postId;
    private String title;
}
