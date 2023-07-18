package com.sparta.sdc.review.controller;

import com.sparta.sdc.review.dto.ReviewRequestDto;
import com.sparta.sdc.review.dto.ReviewResponseDto;
import com.sparta.sdc.review.service.ReviewService;
import com.sparta.sdc.user.security.UserDetailsImpl;
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
    @PostMapping("/{id}/review")
    public ResponseEntity<ReviewResponseDto> createReview(@RequestBody ReviewRequestDto reviewRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){

    }
}
