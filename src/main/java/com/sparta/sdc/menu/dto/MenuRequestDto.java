package com.sparta.sdc.menu.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuRequestDto {

    private String menuname;

    private int menuprice;
}
