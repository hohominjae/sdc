package com.sparta.sdc.review.repository;

import com.sparta.sdc.review.entity.Review;
import com.sparta.sdc.shop.entity.Shop;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByUserAndShop(User user, Shop shop);
}
