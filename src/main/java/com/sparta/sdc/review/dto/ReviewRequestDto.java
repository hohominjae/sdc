package com.sparta.sdc.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//파라미터가 없는 기본 생성자를 생성
@NoArgsConstructor
//모든 필드값을 파라미터로 받는 생성자를 만듦
@AllArgsConstructor
public class ReviewRequestDto {
    // 리뷰
    private String Review;
}
