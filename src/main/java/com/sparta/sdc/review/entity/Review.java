package com.sparta.sdc.review.entity;

import com.sparta.sdc.common.timestamp.Timestamped;
import com.sparta.sdc.order.entity.Order;
import com.sparta.sdc.shop.entity.Shop;
import com.sparta.sdc.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "review")
public class Review extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ReviewId;

    @Column(nullable = false)
    private String review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @OneToMany(mappedBy = "Menu")
    private Menu menu;

    @OneToOne
    private Order order;

    public Review(User user, Shop shop){
        this.user = user;
        this.shop = shop;
    }

}
