//package com.sparta.sdc.email;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//
//@Slf4j
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/api/sdc")
//public class NaverEmailController {
//    private final NaverEmailService naverEmailService;
//
//    @PostMapping("signup/mailConfirm")
//    @ResponseBody
//    public String mailConfirm(@RequestParam String email) throws Exception {
//        log.info("이메일 " + email );
//
//        String code = naverEmailService.sendSimpleMessage(email);
//        log.info("인증코드 : " + code);
//
//        return code;
//    }
//}
