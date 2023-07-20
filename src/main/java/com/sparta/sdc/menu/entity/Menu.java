package com.sparta.sdc.menu.entity;

import com.sparta.sdc.menu.dto.MenuRequestDto;
import com.sparta.sdc.order.entity.Order;
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
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "menuName") //, nullable = false
    private String menuname;
    @Column(name = "menuPrice", nullable = false)
    private int menuprice;
    @Column(name = "menuum", nullable = false)
    private int menunum;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)    //@Column
    private User user;
    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Menu(String menuname, int menuprice, int menunum, User user){
        this.menuname = menuname;
        this.menuprice = menuprice;
        this.menunum = menunum;
        this.user = user;
    }
    public void update(MenuRequestDto menuRequestDto) {
        this.menuname = menuRequestDto.getMenuname();
        this.menuprice = menuRequestDto.getMenuprice();
        this.menunum = menuRequestDto.getMenunum();
    }
}
