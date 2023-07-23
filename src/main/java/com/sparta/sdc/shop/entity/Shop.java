package com.sparta.sdc.shop.entity;

import com.sparta.sdc.menu.entity.Menu;
import com.sparta.sdc.review.entity.Review;
import com.sparta.sdc.shop.dto.ShopRequestDto;
import com.sparta.sdc.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "shop_tb")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shopName", nullable = false) //
    private String shopName;

    @Column(name = "shopNumber", nullable = false)
    private int shopNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "delivery", nullable = false)
    private String delivery;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)    //@Column
    private User user;

    @OneToMany(mappedBy = "shop")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "shop")
    private List<Menu> menus = new ArrayList<>();

    @Builder
    public Shop(String shopName, int shopNumber, String address, String delivery, User user){
        this.shopName = shopName;
        this.shopNumber = shopNumber;
        this.address = address;
        this.delivery = delivery;
        this.user = user;
    }
  
    public void update(ShopRequestDto shopRequestDto) {
        this.shopName = shopRequestDto.getShopName();
        this.shopNumber = shopRequestDto.getShopNumber();
        this.address = shopRequestDto.getAddress();
        this.delivery = shopRequestDto.getDelivery();
    }
}