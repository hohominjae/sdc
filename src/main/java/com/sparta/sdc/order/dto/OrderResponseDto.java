package com.sparta.sdc.order.dto;

import com.sparta.sdc.menu.dto.MenuResponseDto;
import com.sparta.sdc.menu.entity.Menu;
import com.sparta.sdc.order.entity.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderResponseDto {
    private int totalprice;
    private List<MenuResponseDto> menus;

    public OrderResponseDto(Order order){
        this.totalprice = order.getTotalprice();
        this.menus = order.getMenus().stream().map(MenuResponseDto::new).toList();
    }
}
