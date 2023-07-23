package com.sparta.sdc.user.dto;

import com.sparta.sdc.user.entity.ProfilePassword;
import com.sparta.sdc.user.entity.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class EditPasswordResponseDto {

    private String password;


    public EditPasswordResponseDto(User user){
        this.password = user.getPassword();
    }

}
