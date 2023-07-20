package com.sparta.sdc.order.entity;

import com.sparta.sdc.menu.entity.Menu;
import com.sparta.sdc.review.entity.Review;
import com.sparta.sdc.shop.entity.Shop;
import com.sparta.sdc.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_tb")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int totalprice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @OneToMany(mappedBy = "order")
    private List<Order_Menu> orders = new ArrayList<>();

    //Order, Reivew 관계에서 오류생기면 아래 주석처리
//    @OneToOne
//    private Review review;
}


