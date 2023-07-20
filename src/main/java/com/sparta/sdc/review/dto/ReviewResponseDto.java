package com.sparta.sdc.review.dto;

import com.sparta.sdc.review.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReviewResponseDto{

    private Long reviewId;
    private String review;
    private LocalDateTime createdate;
    private LocalDateTime modifydate;
    private String username;


    public ReviewResponseDto(Review review, String username){
        this.reviewId = review.getReviewId();
        this.review =review.getReview();
        this.createdate = getCreatedate();
        this.modifydate = getModifydate();
        this.username = username;

    }

    public ReviewResponseDto(Review review) {
        this.reviewId = review.getReviewId();
        this.review =review.getReview();
        this.createdate = getCreatedate();
        this.modifydate = getModifydate();
    }
}
