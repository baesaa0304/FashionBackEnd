package Fasion.backend.service;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EmailService {
    public void sendVerificationEmail(String email, String userId) {
        String token = UUID.randomUUID().toString(); // 토큰 생성
        String verificationUrl = "http://yourdomain.com/api/members/verify/" + userId; // 인증 URL
        String subject = "Email Verification";
        String body = "Please verify your email by clicking the link: " + verificationUrl;

        // TODO: 실제 이메일 발송 로직 구현
        System.out.println("Sending email to: " + email);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }
}
