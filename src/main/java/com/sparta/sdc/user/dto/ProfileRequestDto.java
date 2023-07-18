package com.sparta.sdc.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequestDto {

    private String nickname;
    private String address;

    public ProfileRequestDto(String nickname, String address){
        this.nickname = nickname;
        this.address = address;
    }

}
