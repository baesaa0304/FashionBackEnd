package Fasion.backend.web;


import Fasion.backend.dto.Member.MemberSignUpDto;
import Fasion.backend.dto.VerifyCodeDto;
import Fasion.backend.exception.DuplicateMemberIdException;
import Fasion.backend.service.EmailService;
import Fasion.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final EmailService emailService;

    @GetMapping
    public void signUp() {
        log.info("singUP() GET");
    }
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody MemberSignUpDto dto) {
        try {
            String userId = memberService.registerMember(dto);
            // 이메일 인증 발송
            memberService.verifyEmail(dto.getEmail()); // 이메일 전송
            return ResponseEntity.ok("회원가입 성공, 이메일 인증을 해주세요!");
        } catch (DuplicateMemberIdException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 아이디입니다.");
        } catch (Exception e) {
            log.error("회원가입 중 오류 발생: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 실패, 다시 시도해주세요.");
        }
    }


    @GetMapping("/verify/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable VerifyCodeDto verifyCodeDto) {
        boolean isVerified = emailService.verifyAuthCode(verifyCodeDto.getEmail(), verifyCodeDto.getAuthCode());
        if (isVerified) {
            return ResponseEntity.ok("이메일 인증 성공!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이메일 인증 실패.");
    }



}
