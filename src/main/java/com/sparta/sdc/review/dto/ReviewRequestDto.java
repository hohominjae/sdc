package com.sparta.sdc.review.dto;

import com.sparta.sdc.menu.dto.MenuResponseDto;
import com.sparta.sdc.review.entity.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReviewRequestDto {
    // 리뷰
    private String review;
}


