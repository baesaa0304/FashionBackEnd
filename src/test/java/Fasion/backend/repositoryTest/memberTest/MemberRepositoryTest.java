package Fasion.backend.repositoryTest.memberTest;

import Fasion.backend.domain.member.Gender;
import Fasion.backend.domain.member.Member;
import Fasion.backend.domain.member.Role;
import Fasion.backend.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    //@Test
    public void testRegisterMember() {
        // 회원 정보를 준비합니다.
        Member member = Member.builder()
                .userId("baegaa1224")
                .password("sunny0304@")
                .nickName("sun young bae")
                .email("baesaa0304@naver.com")
                .birthday(LocalDate.of(2000, 3, 4))
                .gender(Gender.MALE)
                //.role(Role.USER)  // 적절한 Role을 지정합니다.
                .build();

        // 회원 저장
        Member savedMember = memberRepository.save(member);

        // 결과 검증
        assertThat(savedMember).isNotNull();
        assertThat(savedMember.getUserId()).isEqualTo("baegaa1224");
    }
}
