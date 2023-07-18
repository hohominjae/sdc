package com.sparta.sdc.review.service;

import com.sparta.sdc.review.dto.ReviewRequestDto;
import com.sparta.sdc.review.dto.ReviewResponseDto;
import com.sparta.sdc.review.entity.Review;
import com.sparta.sdc.review.repository.ReviewRepository;
import com.sparta.sdc.shop.entity.Shop;
import com.sparta.sdc.shop.repository.ShopRepository;
import com.sparta.sdc.user.entity.User;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ShopRepository shopRepository;

    //리뷰 작성
    public ReviewResponseDto createReview(ReviewRequestDto requestDto, User user){

        Shop shop = shopService.findShop(requestDto,getShopId());

        Review review = new Review(requestDto.getBody());
        review.setUser(user);
        review.setShop(shop);

        reviewRepository.save(review);

        return new ReviewResponseDto(review);
    }

    //리뷰 중복 테스트
    @Transactional
    public void createReview(Long id, User user){

        //가게를 먼저 찾고
        Shop shop = findShop(id);

        //가게에 리뷰가 달려있는지 체크한다.
        Optional<Review> reviewOptional = checkReview(user, shop);

        if (reviewOptional.isPresent()){
            throw new IllegalArgumentException("댓글은 한개만 가능합니다");
        } else {
            Review review = new Review(user, shop);
            reviewRepository.save(review);
                    }
    }

    //가게를 찾는 메서드
    public Shop findShop(Long id) {
        return shopRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("댓글은 주문자만 가능합니다!")
        );
    }

    //댓글에 리뷰를 확인 메서드
    public Optional<Review> checkReview(User user, Shop shop) {
        return reviewRepository.findByUserAndShop(user, shop);
    }
}
