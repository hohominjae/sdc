package com.sparta.sdc.review.entity;

import com.sparta.sdc.common.timestamp.Timestamped;
import com.sparta.sdc.menu.entity.Menu;
import com.sparta.sdc.order.entity.Order;
import com.sparta.sdc.shop.entity.Shop;
import com.sparta.sdc.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "review_tb")
public class Review extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(nullable = false)
    private String review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    //mappedBy는 양방향 관계를 나타내줌.
    @OneToMany
    @JoinColumn(name = "menu_id")
    private List<Menu> menus = new ArrayList<>();


    //빌더는 생성자를 대체해서 객체생성
    //생성자가 많은경우 가독성이 좋지 않다.
    //순서에 종속적이지 않다.
    @Builder
    public Review(String review, Order order, User user){
        this.review = review;
        this.order = order;
        this.user = user;
    }
}