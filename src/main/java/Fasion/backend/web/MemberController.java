package Fasion.backend.web;


import Fasion.backend.dto.Member.MemberSignUpDto;
import Fasion.backend.exception.DuplicateMemberIdException;
import Fasion.backend.service.EmailService;
import Fasion.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;
    private final EmailService emailService;

    @GetMapping
    public void signUp() {
        log.info("singUP() GET");
    }
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody MemberSignUpDto dto) {
        try {
            String userId = memberService.registerMember(dto);
            // 이메일 인증 발송
            emailService.sendVerificationEmail(dto.getEmail(), userId);
            return ResponseEntity.ok("회원가입 성공, 이메일 인증을 해주세요!");
        } catch (DuplicateMemberIdException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 아이디입니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 실패, 다시 시도해주세요.");
        }
    }
    @GetMapping("/verify/{userId}")
    public ResponseEntity<String> verifyEmail(@PathVariable String userId) {
        boolean verified = memberService.verifyEmail(userId);
        if (verified) {
            return ResponseEntity.ok("이메일 인증 성공!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이메일 인증 실패.");
    }



}
