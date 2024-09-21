package Fasion.backend.web;


import Fasion.backend.dto.Member.MemberSignUpDto;
import Fasion.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class MemberController {
    private MemberService memberService;

    @GetMapping
    public void signUp() {
        log.info("singUP() GET");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody MemberSignUpDto dto) {
        log.info("signUp(dto={} POST" , dto);

        // TOOD: 회원 가입 서비스 호출
        String user_id = memberService.registerMember(dto);
        log.info("회원 가입 id={}" , user_id);

        // 회원 가입 이후에 로그인 화면으로 이동(redirect):
        return ResponseEntity.ok("생성 완료 ID: " + user_id);
    }
}
