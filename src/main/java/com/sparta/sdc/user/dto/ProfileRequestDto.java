package com.sparta.sdc.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequestDto {

    private String nickName;
    private String address;

    public ProfileRequestDto(String nickName, String address){
        this.nickName = nickName;
        this.address = address;
    }

}
