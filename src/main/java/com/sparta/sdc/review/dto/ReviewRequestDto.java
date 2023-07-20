package com.sparta.sdc.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDto {
    // 리뷰
    private String review;
    private Long orderId;
}


