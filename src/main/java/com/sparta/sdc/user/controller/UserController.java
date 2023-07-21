package com.sparta.sdc.user.controller;

import com.sparta.sdc.user.dto.ApiResponseDto;
import com.sparta.sdc.user.dto.AuthRequestDto;
import com.sparta.sdc.user.jwtUtil.JwtUtil;
import com.sparta.sdc.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sdc")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;


    @PostMapping("/signup") //회원 가입
    public ResponseEntity<ApiResponseDto> signUp( @RequestBody @Valid AuthRequestDto requestDto) {
        try {
            userService.signup(requestDto);
            return ResponseEntity.status(201).body(new ApiResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("중복된 username 입니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

//    @ResponseBody
//    @PostMapping("/signup/emailCheck")
//    public ResponseEntity<ApiResponseDto> EmailCheck(@RequestBody EmailCheckRequestDto emailCheckRequestDto) throws MessagingException, UnsupportedEncodingException {
//        String authCode = userService.sendEmail(emailCheckRequestDto.getEmail());
//        return new ApiResponseDto(authCode);	// Response body에 값을 반환
//    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto> login(@RequestBody AuthRequestDto loginRequestDto, HttpServletResponse response) {
        try {
            userService.login(loginRequestDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("회원을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST.value()));
        }

        //JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(loginRequestDto.getUserName(), loginRequestDto.getRole()));

        return ResponseEntity.ok().body(new ApiResponseDto("로그인 성공", HttpStatus.CREATED.value()));
    }
}
