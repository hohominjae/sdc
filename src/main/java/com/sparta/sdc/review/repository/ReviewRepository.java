package com.sparta.sdc.review.repository;

import com.sparta.sdc.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
