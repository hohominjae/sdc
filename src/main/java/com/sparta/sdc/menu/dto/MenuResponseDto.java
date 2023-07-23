package com.sparta.sdc.menu.dto;

import com.sparta.sdc.menu.entity.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuResponseDto {

    private String menuname;

    private int menuprice;

    private int menunum;

    public MenuResponseDto(Menu menu){
        this.menuname = menu.getMenuname();
        this.menuprice = menu.getMenuprice();
        this.menunum = menu.getMenunum();

    }
}
