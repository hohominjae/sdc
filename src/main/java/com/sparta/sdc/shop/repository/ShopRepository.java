package com.sparta.sdc.shop.repository;

import com.sparta.sdc.shop.entity.Shop;
import com.sparta.sdc.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  ShopRepository extends JpaRepository<Shop, Long> {
    Shop findByUserId(Long id);
}