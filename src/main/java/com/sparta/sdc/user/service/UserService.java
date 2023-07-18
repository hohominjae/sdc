package com.sparta.sdc.user.service;

import com.sparta.sdc.user.dto.AuthRequestDto;
import com.sparta.sdc.user.dto.ProfileRequestDto;
import com.sparta.sdc.user.dto.ProfileResponseDto;
import com.sparta.sdc.user.entity.User;
import com.sparta.sdc.user.entity.UserRoleEnum;
import com.sparta.sdc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;

    public final UserRepository userRepository;
    public void signup(AuthRequestDto requestDto) {
        String userName = requestDto.getUserName();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String nickname = requestDto.getNickName();
        String email = requestDto.getEmail();
        String address = requestDto.getAddress();
        UserRoleEnum role = requestDto.getRole();

        // 회원 중복 확인
        Optional<User> checkuserName = userRepository.findByUserName(userName);
        if (checkuserName.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
        // email 중복확인
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }
        //사용자 등록
        User user = new User(userName, password, nickname, email, address, role);
        userRepository.save(user);
    }

    public void login(AuthRequestDto requestDto) {
        String userName = requestDto.getUserName();
        String password = requestDto.getPassword();
        //사용자 확인(useername이 없는 경우)
        User user = userRepository.findByUserName(userName).orElseThrow(() -> new IllegalArgumentException("등록된 사용자가 없습니다."));

        //비밀번호 확인(password가 다른 경우)
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    // 프로필 보기
    public ProfileResponseDto getProfile(Long id) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")
        );

        return new ProfileResponseDto(user);
    }

    // 프로필 수정하기(닉네임, 주소)
    @Transactional
    public ProfileResponseDto updateProfile(Long profileId, ProfileRequestDto requestDto) {

        User user = userRepository.findById(profileId).orElseThrow(
                () -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다.")
        );

        user.update(requestDto);
        return new ProfileResponseDto(user);

    }




}
