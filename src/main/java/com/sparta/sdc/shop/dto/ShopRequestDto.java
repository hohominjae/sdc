package com.sparta.sdc.shop.dto;

import com.sparta.sdc.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ShopRequestDto {
    private String shopName;
    private int shopNumber;
    private String address;
    private boolean delivery; // 1 : TRUE(배달), 0 : FALSE(포장)
    private User user;

    @Builder
    public ShopRequestDto(String shopname, int shopnumber, String address, boolean delivery, User user) {
        this.shopName = shopname;
        this.shopNumber = shopnumber;
        this.address = address;
        this.delivery = delivery;
        this.user = user;
    }

}