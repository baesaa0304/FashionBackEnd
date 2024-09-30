package Fasion.backend.repository;

import Fasion.backend.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member>findByMemberId (String userId);

    /**
     * ID 그리고 이메일 인증확인
     * @param userId email
     * @return
     */
    boolean existsByMemberId(String userId);

}
