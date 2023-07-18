package com.sparta.sdc.shop.dto;

import com.sparta.sdc.shop.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShopResponseDto {
    private Long id;
    private String shopname;
    private int shopnumber;
    private String address;
    private String delivery;
    private String username;



    public ShopResponseDto(Shop shop) {
        this.id = shop.getId();
        this.shopname = shop.getShopname();
        this.shopnumber = shop.getShopnumber();
        this.address = shop.getAddress();
        this.delivery = shop.getDelivery();
        this.username = shop.getUser().getUserName();
    }
}
