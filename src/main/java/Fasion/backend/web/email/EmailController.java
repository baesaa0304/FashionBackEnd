package Fasion.backend.web.email;

import Fasion.backend.dto.email.EmailCheckDto;
import Fasion.backend.dto.email.EmailDto;
import Fasion.backend.service.email.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class EmailController {
    private final EmailService emailService;

    /**
     * 이메일 인증하기
     * @param emailDto
     * @return
     */
    @PostMapping("/mailCheck")
    public String emailCheck(@RequestBody @Valid EmailDto emailDto) {
        //log.info("이메일 요청이 왔습니다.");
        //log.info("이메일 인증 이메일 : " + emailDto.getEmail());
        return emailService.joinEmail(emailDto.getEmail());
    }

    /**
     * 이메일로 받은 토크 즉  인증번호 확인
     * @param emailCheckDto
     * @return
     */

    @PostMapping("/mailConcord")
    public String AuthCheck(@RequestBody @Valid EmailCheckDto emailCheckDto){
        boolean checked=emailService.CheckAuthNum(emailCheckDto.getEmail(), emailCheckDto.getAuthNum());
        if (checked) {
            return "이메일 인증 성공!";
        } else {
            throw new NullPointerException("유효시간이 지났거나 인증이 만료되었습니다. 다시 시도해주세요.");
        }
    }


}