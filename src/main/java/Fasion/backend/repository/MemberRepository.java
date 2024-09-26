package Fasion.backend.repository;

import Fasion.backend.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member>findByUserId (String userId);
    Optional<Member> findByEmail(String email);

    /**
     * ID 그리고 이메일 중복 확인
     * @param userId email
     * @return
     */
    boolean existsByUserId(String userId);
    boolean existsByEmail(String email);
}
