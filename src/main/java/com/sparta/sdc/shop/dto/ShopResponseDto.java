package com.sparta.sdc.shop.dto;

import com.sparta.sdc.menu.dto.MenuResponseDto;
import com.sparta.sdc.order.dto.OrderResponseDto;
import com.sparta.sdc.review.dto.ReviewResponseDto;
import com.sparta.sdc.shop.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ShopResponseDto {

    private Long id;
    private String shopName;
    private int shopNumber;
    private String address;
    private String delivery;
    private String username;
    private List<MenuResponseDto> menus;
    private List<ReviewResponseDto> reviews;
    private List<ShopResponseDto> shopsList;

    public ShopResponseDto(Shop shop) {
        this.id = shop.getId();
        this.shopName = shop.getShopName();
        this.shopNumber = shop.getShopNumber();
        this.address = shop.getAddress();
        this.delivery = shop.getDelivery();
        this.username = shop.getUser().getUserName();
        this.menus = shop.getMenus().stream().map(MenuResponseDto::new).toList();
        this.reviews = shop.getReviews().stream().map(ReviewResponseDto::new).toList();
    }
  
    public ShopResponseDto(List<ShopResponseDto> shopList) {
        this.shopsList = shopList;
    }
}