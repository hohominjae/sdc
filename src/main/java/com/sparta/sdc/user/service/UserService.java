package com.sparta.sdc.user.service;

import com.sparta.sdc.user.dto.AuthRequestDto;
import com.sparta.sdc.user.dto.EditPasswordRequestDto;
import com.sparta.sdc.user.dto.ProfileRequestDto;
import com.sparta.sdc.user.dto.ProfileResponseDto;
import com.sparta.sdc.user.entity.ProfilePassword;
import com.sparta.sdc.user.entity.User;
import com.sparta.sdc.user.entity.UserRoleEnum;
import com.sparta.sdc.user.repository.ProfilePasswordRepository;
import com.sparta.sdc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;

    public final UserRepository userRepository;

    public final ProfilePasswordRepository profilePasswordRepository;

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

        ProfilePassword profilePassword = new ProfilePassword(password, user);
        profilePasswordRepository.save(profilePassword);

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
    public void updateProfile(ProfileRequestDto requestDto, User user) {
        User userItem = userRepository.findById(user.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다.")
        );

        userItem.setNickName(requestDto.getNickName());
        userItem.setAddress(requestDto.getAddress());

    }

    // 비밀번호 수정
    @Transactional
    public void updatePassword(EditPasswordRequestDto requestDto, User user) {

        User userItem = userRepository.findById(user.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다.")
        );
        ProfilePassword profilePassword = profilePasswordRepository.findByUser(userItem);

        if(passwordEncoder.matches(requestDto.getPassword(), userItem.getPassword())){
            String newPassword = passwordEncoder.encode(requestDto.getNewPassword());
            String password = checkDuplicatePassword(requestDto.getNewPassword(), profilePassword);

            if(password.equals(requestDto.getOneMorePassword())){
                if(profilePassword.getSecondPassword().equals(" ")){
                    profilePassword.addSecondPassword(newPassword);
                }else if(profilePassword.getThirdPassword().equals(" ")){
                    profilePassword.addThirdPassword(newPassword);
                }else{
                    profilePassword.changePassword(newPassword);
                }
                userItem.setPassword(newPassword);
            }else{
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        }else{
            throw new IllegalArgumentException("기존비밀번호가 일치하지 않습니다.");
        }
    }

    private String checkDuplicatePassword(String newPassword, ProfilePassword profilePassword) {
        // 최근 수정한 비밀번호와 같은 지 확인
        // 1차 점검
        //바꾸려는비밀번호가 firstPassword와 일치할 경우
        if (passwordEncoder.matches(newPassword, profilePassword.getFirstPassword())) {
            throw new IllegalArgumentException("최근 수정한 비밀번호와 동일합니다.");
        }
            //바꾸려는비밀번호가 firsPassword와 일치하지 않은경우
            // SECOND가 공백이 아닌경우
                    else if (!profilePassword.getSecondPassword().equals(" ")) {
            // 바꾸려는 비밀번호가 Second와 일치할경우
            if (passwordEncoder.matches(newPassword, profilePassword.getSecondPassword())) {
                throw new IllegalArgumentException("최근 수정한 비밀번호와 동일합니다.");
            }
            // Third가 공백이 아닌경우
            else if (!profilePassword.getThirdPassword().equals(" ")) {
                if (passwordEncoder.matches(newPassword, profilePassword.getThirdPassword())) {
                    throw new IllegalArgumentException("최근 수정한 비밀번호와 동일합니다.");
                } else {
                    return newPassword;
                }
            } else //third가 공백인경우
                return newPassword;
        }else//Second가 공백인경우
            return newPassword;
    }
}
