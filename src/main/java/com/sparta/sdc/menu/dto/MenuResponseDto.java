package com.sparta.sdc.menu.dto;

import com.sparta.sdc.menu.entity.Menu;
import com.sparta.sdc.shop.dto.ShopResponseDto;
import com.sparta.sdc.shop.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MenuResponseDto {
    private Long id;
    private String menuname;
    private int menuprice;
    private int menunum;
    private String username;

    private List<MenuResponseDto> menusList;


    public MenuResponseDto(Menu menu) {
        this.id = menu.getId();
        this.menuname = menu.getMenuname();
        this.menuprice = menu.getMenuprice();
        this.menunum = menu.getMenunum();
        this.username = menu.getUser().getUserName();
    }


    public MenuResponseDto(List<MenuResponseDto> menusList) {
        this.menusList = menusList;
    }
}
