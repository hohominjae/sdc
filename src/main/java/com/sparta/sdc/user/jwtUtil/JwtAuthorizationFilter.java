package com.sparta.sdc.user.jwtUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.sdc.user.dto.ApiResponseDto;
import com.sparta.sdc.common.timestamp.security.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j(topic = "JWT 검증 및 인가")
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final ObjectMapper objectMapper; //필드 추가. 객체를 제이슨 형태로 바꿔주는 클래스 라이브러리
    public JwtAuthorizationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService, ObjectMapper objectMapper) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.objectMapper = objectMapper; //생성자로 주입
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtil.resolveToken(request);

        if (token != null) {
            if (!jwtUtil.validateToken(token)) { //토큰 체크
                ApiResponseDto responseDto = new ApiResponseDto("토큰이 유효하지 않습니다.", HttpStatus.BAD_REQUEST.value()); //400응답
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json; charset=UTF-8"); //응답하는 본문의 타입 : 제이슨 타입으로, 한글이 깨지지 않게 charset=UTF-8로
                response.getWriter().write(objectMapper.writeValueAsString(responseDto)); //응답 본문 작성. objectMapper 메소드 사용 : 자바 객체를 제이슨 타입의 문자열로 변환해 주는 메소드. _jsonFactory 사용
                return;
            }
            Claims info = jwtUtil.getUserInfoFromToken(token);
            setAuthentication(info.getSubject());
        }
        filterChain.doFilter(request, response);
    }
    //인증 처리
    private void setAuthentication(String userName) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(userName);
        context.setAuthentication(authentication);
        // userName -> user 조회 -> userDetails 에 담고 -> authentication의 principal 에 담고
        // -> securityContent 에 담고 -> SecurityContextHolder 에 담고
        // -> 이제 @AuthenticationPrincipal 로 조회할 수 있음
        SecurityContextHolder.setContext(context);
    }
    //인증 객체 생성
    private Authentication createAuthentication(String userName) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}

