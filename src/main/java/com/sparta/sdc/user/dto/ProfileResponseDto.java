package com.sparta.sdc.user.dto;

import com.sparta.sdc.user.entity.User;
import lombok.Getter;

@Getter
public class ProfileResponseDto {

    private String nickname;
    private String address;

    // 저장된 user정보를 entity를 통해 가져온다.
    public ProfileResponseDto(User user){
        this.nickname = user.getNickname();
        this.address = user.getAddress();
    }

}
