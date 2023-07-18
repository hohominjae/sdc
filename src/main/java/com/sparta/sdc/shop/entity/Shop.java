package com.sparta.sdc.shop.entity;

import com.sparta.sdc.shop.dto.ShopRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String shopname;
    @Column(nullable = false)
    private int shopnumber;
    @Column
    private String address;
    @Column
    private String delivery;
    //@ManyToOne(fetch = FetchType.LAZY)
    //@Column
    //private User user;

    @Builder
    public Shop(String shopname, Integer shopnumber, String address, String delivery ){ //User user
        this.shopname = shopname;
        this.shopnumber = shopnumber;
        this.address = address;
        this.delivery = delivery;
        //this.user = user;
    }
    public void update(ShopRequestDto shopRequestDto) {
        this.shopname = shopRequestDto.getShopname();
        this.shopnumber = shopRequestDto.getShopnumber();
        this.address = shopRequestDto.getAddress();
        this.delivery = shopRequestDto.getDelivery();
    }
}