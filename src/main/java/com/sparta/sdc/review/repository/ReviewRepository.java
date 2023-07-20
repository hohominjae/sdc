package com.sparta.sdc.review.repository;

import com.sparta.sdc.order.entity.Order;
import com.sparta.sdc.review.entity.Review;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByOrder(Order order);

}
