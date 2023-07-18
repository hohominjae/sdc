package com.sparta.sdc.review.dto;

import com.sparta.sdc.review.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewResponseDto {

    private Long id;
    private String review;

    public ReviewResponseDto(Review review){
        this.id = review.getId();
        this.review =review.getReview();


    }
}
