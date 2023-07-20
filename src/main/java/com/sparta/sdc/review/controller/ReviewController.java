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
    //댓글 작성기능
}