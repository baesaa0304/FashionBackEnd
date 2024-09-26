package Fasion.backend.repository;

import Fasion.backend.domain.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member>findByUserId (String userId);
    Optional<Member> findByEmail(String email); // 이메일로 회원 검색
    boolean existsByUserId(String userId); // 인증 확인

}
