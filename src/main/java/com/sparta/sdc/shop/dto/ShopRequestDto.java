package com.sparta.sdc.shop.dto;

import com.sparta.sdc.menu.dto.MenuResponseDto;
import com.sparta.sdc.order.dto.OrderResponseDto;
import com.sparta.sdc.review.dto.ReviewResponseDto;
import com.sparta.sdc.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ShopRequestDto {
    private String shopName;
    private int shopNumber;
    private String address;
    private String delivery;
    private User user;

    @Builder
    public ShopRequestDto(String shopName, int shopNumber, String address, String delivery, User user) {
        this.shopName = shopName;
        this.shopNumber = shopNumber;
        this.address = address;
        this.delivery = delivery;
        this.user = user;
    }
}