package Fasion.backend.service.email;


import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

import lombok.RequiredArgsConstructor;



@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final JavaMailSender mailSender;
    private final RedisUtil redisUtil;
    private int authNumber;

    public boolean CheckAuthNum(String email,String authNum){
        String storedAuthNum = redisUtil.getData(email);
        log.info("Stored AuthNum: " + storedAuthNum); // Redis에서 가져온 인증 번호 로그
        log.info("Input AuthNum: " + authNum);

        return storedAuthNum != null && storedAuthNum.equals(authNum);
    }


    //임의의 6자리 양수를 반환합니다.
    public void makeRandomNumber() {
        Random r = new Random();
        String randomNumber = "";
        for(int i = 0; i < 6; i++) {
            randomNumber += Integer.toString(r.nextInt(10));
        }

        authNumber = Integer.parseInt(randomNumber);
    }

    //mail을 어디서 보내는지, 어디로 보내는지 , 인증 번호를 html 형식으로 어떻게 보내는지 작성합니다.
    public String joinEmail(String email) {
        makeRandomNumber();
        String setFrom = "dev.limezero00@gmail.com"; // email-config에 설정한 자신의 이메일 주소를 입력
        String toMail = email;
        String title = "회원 가입 인증 이메일 입니다."; // 이메일 제목
        String content =
                "<div style='font-family: Arial, sans-serif; text-align: center;'>" +
                        "<h2>저희 서비스를 이용해 주셔서 감사합니다. </h2>" +
                        "<p style='font-size: 16px;'>아래의 인증 번호를 입력해 주세요.</p>" +
                        "<br>" +
                        "<div style='background-color: #f4f4f4; padding: 20px; display: inline-block;'>" +
                        "<p style='font-size: 30px; font-weight: bold; color: #333; margin: 0;'>" + authNumber + "</p>" +
                        "</div>" +
                        "<br><br>" +
                        "<p style='font-size: 14px; color: #555;'>인증번호를 올바르게 입력해 주세요.</p>" +
                        "</div>";
        // Redis에 인증번호 저장
        redisUtil.setDataExpire(email, Integer.toString(authNumber), 300);
        mailSend(setFrom, toMail, title, content);
        return Integer.toString(authNumber);
    }


    //이메일을 전송합니다.
    public void mailSend(String setFrom, String toMail, String title, String content) {
        MimeMessage message = mailSender.createMimeMessage();//JavaMailSender 객체를 사용하여 MimeMessage 객체를 생성
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");//이메일 메시지와 관련된 설정을 수행합니다.
            // true를 전달하여 multipart 형식의 메시지를 지원하고, "utf-8"을 전달하여 문자 인코딩을 설정
            helper.setFrom(setFrom);//이메일의 발신자 주소 설정
            helper.setTo(toMail);//이메일의 수신자 주소 설정
            helper.setSubject(title);//이메일의 제목을 설정
            helper.setText(content,true);//이메일의 내용 설정 두 번째 매개 변수에 true를 설정하여 html 설정으로한다.
            mailSender.send(message);
        } catch (MessagingException e) {//이메일 서버에 연결할 수 없거나, 잘못된 이메일 주소를 사용하거나, 인증 오류가 발생하는 등 오류
            // 이러한 경우 MessagingException이 발생
            e.printStackTrace();//e.printStackTrace()는 예외를 기본 오류 스트림에 출력하는 메서드
        }
        redisUtil.setDataExpire(Integer.toString(authNumber),toMail,60*5L);

    }

}