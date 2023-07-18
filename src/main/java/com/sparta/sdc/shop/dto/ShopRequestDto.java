package com.sparta.sdc.shop.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ShopRequestDto {
    private String shopname;
    private int shopnumber;
    private String address;
    private String delivery;
    //private User user;

    @Builder
    public ShopRequestDto(String shopname, Integer shopnumber, String address, String delivery) {
        this.shopname = shopname;
        this.shopnumber = shopnumber;
        this.address = address;
        this.delivery = delivery;
        //this.user = user;
    }

}