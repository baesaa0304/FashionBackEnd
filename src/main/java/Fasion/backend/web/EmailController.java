package Fasion.backend.web;

import Fasion.backend.dto.EmailDto;
import Fasion.backend.dto.VerifyCodeDto;
import Fasion.backend.service.EmailService;
import Fasion.backend.service.MemberService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member/signUp")
public class EmailController {

    private final EmailService emailService;
    private final MemberService memberService;

    @ResponseBody
    @PostMapping("/emailCheck") // 이 부분은 각자 바꿔주시면 됩니다.
    public String emailCheck(@RequestBody EmailDto mailDTO) throws MessagingException, UnsupportedEncodingException {
        String authCode = emailService.sendSimpleMessage(mailDTO.getEmail());
        return authCode; // Response body에 값을 반환
    }

    @ResponseBody
    @PostMapping("/verifyCode")
    public ResponseEntity<String> verifyCode(@RequestBody VerifyCodeDto verifyCodeDto) {
        // 사용자가 입력한 인증번호를 확인
        boolean isVerified = emailService.verifyAuthCode(verifyCodeDto.getEmail(), verifyCodeDto.getAuthCode());

        if (isVerified) {
            // 인증 성공 시 이메일 인증 상태 업데이트
            memberService.verifyEmail(verifyCodeDto.getEmail());
            return ResponseEntity.ok("인증에 성공했습니다.");
        } else {
            // 인증 실패
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("인증번호가 일치하지 않습니다.");
        }
    }
}