package com.sparta.sdc.user.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class EditPasswordRequestDto {


    @Pattern(regexp="^[a-zA-Z0-9~`!@#$%^&*()-_+=]{8,15}$", message="영대소문자와 숫자(0~9) 및 특수문자(~`!@#$%^&*()-_+=)로 이뤄진 8자 이상 15자 이하의 값으로 이뤄졌습니다.")
    private String password; // 기존 password

    @Pattern(regexp="^[a-zA-Z0-9~`!@#$%^&*()-_+=]{8,15}$", message="영대소문자와 숫자(0~9) 및 특수문자(~`!@#$%^&*()-_+=)로 이뤄진 8자 이상 15자 이하의 값으로 이뤄졌습니다.")
    private String newPassword; // 새로운 password

    @Pattern(regexp="^[a-zA-Z0-9~`!@#$%^&*()-_+=]{8,15}$", message="영대소문자와 숫자(0~9) 및 특수문자(~`!@#$%^&*()-_+=)로 이뤄진 8자 이상 15자 이하의 값으로 이뤄졌습니다.")
    private String oneMorePassword; // 새로운 password 한번 더 (확인용)


}
