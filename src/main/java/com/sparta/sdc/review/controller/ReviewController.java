package com.sparta.sdc.review.controller;

import com.sparta.sdc.review.dto.ReviewResponseDto;
import com.sparta.sdc.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sdc")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //댓글 작성기능
    @PostMapping("/{id}/review")
    public ResponseEntity<ReviewResponseDto> createReview(){

    }
}
