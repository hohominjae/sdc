package com.sparta.sdc.user.dto;

import com.sparta.sdc.user.entity.UserRoleEnum;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDto {
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "최소 4자 이상, 10자 이하이며 알파벳 소믄자(a~z), 숫자(0~9)로 구성되어야함." )
    private String userName;

    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+{}:\"<>?,.\\\\/]{8,15}$", message = "최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자로 구성되어야함.")
    public String password;

    public String nickName;

    public String email;

    private String address;

    private UserRoleEnum role; //회원 권한(관리자, 시용자)
}
