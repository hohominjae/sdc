package com.sparta.sdc.shop.repository;

import com.sparta.sdc.shop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ShopRepository extends JpaRepository<Shop, Long> {
}