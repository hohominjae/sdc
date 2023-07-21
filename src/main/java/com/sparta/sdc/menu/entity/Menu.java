package com.sparta.sdc.menu.entity;

import com.sparta.sdc.order.entity.Order;
import com.sparta.sdc.shop.entity.Shop;
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
@Table(name = "menu_tb")
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
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    //cascade = CascadeType.REMOVE -> 참조관계를 지워주는 역할


}

