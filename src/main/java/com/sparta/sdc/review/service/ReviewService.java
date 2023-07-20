package com.sparta.sdc.review.service;

import com.sparta.sdc.common.security.UserDetailsImpl;
import com.sparta.sdc.order.entity.Order;
import com.sparta.sdc.order.repository.OrderRepository;
import com.sparta.sdc.review.dto.ReviewRequestDto;
import com.sparta.sdc.review.dto.ReviewResponseDto;
import com.sparta.sdc.review.entity.Review;
import com.sparta.sdc.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;


    public ReviewResponseDto createReview(UserDetailsImpl userDetails, ReviewRequestDto reviewRequestDto) {
        Order order = orderRepository.findById(reviewRequestDto.getOrderId())
                .orElseThrow(()-> new IllegalArgumentException(
                        "리뷰는 주문후에!!"
                ));
        Review review;
        if (reviewRequestDto.getParentId() == 0){
            review = new Review(reviewRequestDto.getReview(), order, userDetails.getUser());
        } else {
            Review parentReivew = reviewRepository.findById(reviewRequestDto.getParentId()).orElseThrow(
                    ()->new IllegalArgumentException("리뷰가 존재하지 않습니다.")
            );
            if(parentReivew.getOrder().getId() !=reviewRequestDto.getOrderId())
                throw new IllegalArgumentException("잘못된 리뷰입니다.");
            review = new Review(reviewRequestDto.getReview(), order, userDetails.getUser());

            review.addParent(parentReivew);
        }
        reviewRepository.save(review);
        return new ReviewResponseDto(review, userDetails.getUsername());
    }

    public Object updateReview(Long id, ReviewRequestDto reviewRequestDto) {
        Review review = reviewRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("리뷰가 존재하지 않습니다.")
        );
        review.update(reviewRequestDto.getReview());
        reviewRepository.save(review);
        return new ReviewResponseDto(review);
    }
}
//리뷰 중복 테스트
//    @Transactional
//    public void createReview(Long id, Order order){
//
//        //가게를 먼저 찾고
//        Order findOrder = findOrder(id);
//
//        //가게에 리뷰가 달려있는지 체크한다.
//        Optional<Review> reviewOptional = checkReview(order, findOrder);
//
//        if (reviewOptional.isPresent()){
//            throw new IllegalArgumentException("댓글은 한개만 가능합니다");
//        } else {
//            Review review = new Review();
//            reviewRepository.save(review);
//                    }
//    }
