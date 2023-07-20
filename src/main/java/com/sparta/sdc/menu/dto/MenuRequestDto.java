package com.sparta.sdc.menu.dto;

import com.sparta.sdc.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MenuRequestDto {
    private String menuname;
    private int menuprice;
    private int menunum;
    private User user;

    @Builder
    public MenuRequestDto(String menuname, int menuprice, int menunum,User user) {
        this.menuname = menuname;
        this.menuprice = menuprice;
        this.menunum = menunum;
        this.user = user;
    }
}
