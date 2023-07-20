package com.sparta.sdc.menu.entity;

import com.sparta.sdc.order.entity.Order_Menu;
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
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String menuname;

    @Column
    private int menuprice;

    @Column
    private int menunum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @OneToMany(mappedBy = "menu")
    private List<Order_Menu> menus = new ArrayList<>();

}

