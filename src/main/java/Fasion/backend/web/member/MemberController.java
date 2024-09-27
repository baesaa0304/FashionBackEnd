package Fasion.backend.web.member;


import Fasion.backend.dto.member.MemberSignUpDto;
import Fasion.backend.exception.DuplicateMemberIdException;
import Fasion.backend.service.email.EmailService;
import Fasion.backend.service.member.MemberService;
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
        log.info("singUP() POST");
        log.info("dto: {}", dto);
        try {
            memberService.registerMember(dto);
            return ResponseEntity.ok("회원가입 성공");
        } catch (DuplicateMemberIdException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 아이디입니다.");
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 에러");
        }

    }







}
