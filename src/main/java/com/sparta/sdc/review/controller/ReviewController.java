package com.sparta.sdc.review.controller;

import com.sparta.sdc.common.security.UserDetailsImpl;
import com.sparta.sdc.review.dto.ReviewRequestDto;
import com.sparta.sdc.review.dto.ReviewResponseDto;
import com.sparta.sdc.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sdc")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //댓글 작성기능
    @PostMapping("/review")
    public ResponseEntity<ReviewResponseDto> createReview(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody ReviewRequestDto reviewRequestDto){

        return ResponseEntity.ok(reviewService.createReview(userDetails, reviewRequestDto));
    }

    @PutMapping("/review/{id}")
    public ResponseEntity<ReviewResponseDto> updateReview(@PathVariable Long id, @RequestBody ReviewRequestDto reviewRequestDto){

        return ResponseEntity.ok((ReviewResponseDto) reviewService.updateReview(id, reviewRequestDto));
    }
}

