package Fasion.backend.service.member;


import Fasion.backend.domain.member.Member;
import Fasion.backend.dto.member.MemberSignUpDto;
import Fasion.backend.exception.DuplicateMemberIdException;
import Fasion.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;


@RequiredArgsConstructor
@Slf4j
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     * 중복 아이디 확인
     * 가입 후 이메일 인증 시도
     *
     * @param dto
     * @param
     */
    public void registerMember(MemberSignUpDto dto) {
        log.info("registerMember(dto={})" , dto);

        // 중복 아이디 인 경우 !
        if (memberRepository.existsByMemberId(dto.getMemberId())) {
            throw new DuplicateMemberIdException("중복된 아이디입니다. 변경해주세요");
        }
        Member member = Member.builder()
                .memberId(dto.getMemberId())
                .password(passwordEncoder.encode(dto.getPassword()))
                .nickName(dto.getNickName())
                .email(dto.getEmail())
                .birthday(dto.getBirthday())
                .gender(dto.getGender())
                .build();

        memberRepository.save(member);

    }


    /**
     * 회원 아이디가 있는지 없는지 확인하는 메서드
     * @param userId
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        log.info("logUserByUsername(username={})", userId);

        // DB에서 userId로 사용자 정보 검색 (select)
        return memberRepository.findByMemberId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("회원 정보를 찾을 수 없습니다. 아이디: " + userId));
    }

}
