package Fasion.backend.service;


import Fasion.backend.domain.Member.Member;
import Fasion.backend.dto.Member.MemberSignUpDto;
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
    private final ConcurrentHashMap<String, MemberSignUpDto> pendingVerifications = new ConcurrentHashMap<>();

    /**
     * 회원가입
     * 중복 아이디 확인
     * 가입 후 이메일 인증 시도
     * @param dto
     * @return
     */
    public String registerMember(MemberSignUpDto dto) {
        log.info("registerMember(dto={})" , dto);

        // 중복 아이디 인 경우 !
        if (memberRepository.existsByUserId(dto.getUserId())) {
            throw new DuplicateMemberIdException("중복된 아이디입니다.");
        }
        // 이메일 인증을 위한 사용자 정보 임시 저장
        pendingVerifications.put(dto.getUserId(), dto);

        return dto.getUserId(); // 회원가입 ID 반환
    }

    /**
     * 이메일 인증 후 회원가입
     * @param @email
     * @return
     */
    public String verifyEmail(String email) {
        // 여기서 이메일에 해당하는 회원 정보를 가져와서 회원가입 로직을 수행
        // 예를 들어, 이미 이메일로 회원가입을 위한 DTO를 받았다고 가정
        MemberSignUpDto dto = pendingVerifications.get(email);

        if (dto == null) {
            throw new IllegalArgumentException("회원가입 정보가 없습니다."); // 예외 처리
        }

        // 회원정보 저장
        Member member = Member.builder()
                .userId(dto.getUserId())
                .password(passwordEncoder.encode(dto.getPassword()))
                .nickName(dto.getNickName())
                .email(dto.getEmail())
                .birthday(dto.getBirthday())
                .gender(dto.getGender())
                .build();

        memberRepository.save(member);
        return member.getUserId(); // 회원가입 완료 후 사용자 ID 반환
    }










    /**
     * 회원 아이디가 있는지 없는지 확인하는 메서드
     * @param userId
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        log.info("logUserByUsername(username={})", userId);

        // DB에서 userId로 사용자 정보 검색 (select)
        return memberRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("회원 정보를 찾을 수 없습니다. 아이디: " + userId));
    }

}
