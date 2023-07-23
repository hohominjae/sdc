package com.sparta.sdc.menu.entity;

import com.sparta.sdc.menu.dto.MenuRequestDto;
import com.sparta.sdc.order.entity.Order;
import com.sparta.sdc.shop.entity.Shop;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = true)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = true)
    private Order order;

    //cascade = CascadeType.REMOVE -> 참조관계를 지워주는 역할

    public Menu(MenuRequestDto requestDto, Shop shop){
        this.menuname = requestDto.getMenuname();
        this.menuprice = requestDto.getMenuprice();
        this.shop = shop;
    }

    public void update(MenuRequestDto requestDto) {
        this.menuname = requestDto.getMenuname();
        this.menuprice = requestDto.getMenuprice();
    }
}

