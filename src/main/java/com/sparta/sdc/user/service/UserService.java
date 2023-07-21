package com.sparta.sdc.user.service;

import com.sparta.sdc.user.dto.AuthRequestDto;
import com.sparta.sdc.user.entity.User;
import com.sparta.sdc.user.entity.UserRoleEnum;
import com.sparta.sdc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;

    public final UserRepository userRepository;
//    private JavaMailSender emailSender;
//    private String authNum;
//
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

//    // 인증번호 8자리 무작위 생성
//    public void createCode() {
//        Random random = new Random();
//        StringBuffer key = new StringBuffer();
//
//        for(int i=0; i<8; i++) {
//            // 0~2 사이의 값을 랜덤하게 받아와 idx에 집어넣습니다.
//            int idx = random.nextInt(3);
//
//            // 랜덤하게 idx를 받았으면, 그 값을 switchcase를 통해 또 꼬아버립니다.
//            // 숫자와 ASCII 코드를 이용합니다.
//            switch (idx) {
//                case 0 :
//                    // a(97) ~ z(122)
//                    key.append((char) ((int)random.nextInt(26) + 97));
//                    break;
//                case 1:
//                    // A(65) ~ Z(90)
//                    key.append((char) ((int)random.nextInt(26) + 65));
//                    break;
//                case 2:
//                    // 0 ~ 9
//                    key.append(random.nextInt(9));
//                    break;
//            }
//        }
//        authNum = key.toString();
//    }
//
//    // 메일 양식 작성
//    public MimeMessage createEmailForm(String email) throws MessagingException, UnsupportedEncodingException {
//        // 코드를 생성합니다.
//        createCode();
//        String setFrom = "tls9607@gmail.com";	// 보내는 사람
//        String toEmail = email;		// 받는 사람(값 받아옵니다.)
//        String title = "SDC 인증번호";		// 메일 제목
//
//       MimeMessage message = emailSender.createMimeMessage();
//
//        message.addRecipients(MimeMessage.RecipientType.TO, toEmail);	// 받는 사람 설정
//        message.setSubject(title);		// 제목 설정
//
//        // 메일 내용 설정
//        String msgOfEmail="";
//        msgOfEmail += "<div style='margin:20px;'>";
//        msgOfEmail += "<h1> 안녕하세요 SDC 입니다. </h1>";
//        msgOfEmail += "<br>";
//        msgOfEmail += "<p>아래 코드를 입력해주세요<p>";
//        msgOfEmail += "<br>";
//        msgOfEmail += "<p>감사합니다.<p>";
//        msgOfEmail += "<br>";
//        msgOfEmail += "<div align='center' style='border:1px solid black; font-family:verdana';>";
//        msgOfEmail += "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
//        msgOfEmail += "<div style='font-size:130%'>";
//        msgOfEmail += "CODE : <strong>";
//        msgOfEmail += authNum + "</strong><div><br/> ";
//        msgOfEmail += "</div>";
//
//        message.setFrom(setFrom);		// 보내는 사람 설정
//        // 위 String으로 받은 내용을 아래에 넣어 내용을 설정합니다.
//        message.setText(msgOfEmail, "utf-8", "html");
//
//        return message;
//    }
//
//    //실제 메일 전송
//    public String sendEmail(String email) throws MessagingException, UnsupportedEncodingException {
//
//        //메일전송에 필요한 정보 설정
//        MimeMessage emailForm = createEmailForm(email);
//        //실제 메일 전송
//        emailSender.send((MimeMessagePreparator) emailForm);
//
//        return authNum; //인증 코드 반환
//    }
}
