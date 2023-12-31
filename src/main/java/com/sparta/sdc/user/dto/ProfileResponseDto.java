package com.sparta.sdc.user.dto;

import com.sparta.sdc.user.entity.ProfilePassword;
import com.sparta.sdc.user.entity.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProfileResponseDto {

    private String nickname;
    private String address;

    // 저장된 user정보를 entity를 통해 가져온다.
    public ProfileResponseDto(User user){
        this.nickname = user.getNickName();
        this.address = user.getAddress();
    }

}
