package Fasion.backend.service;


import Fasion.backend.domain.Member.Member;
import Fasion.backend.dto.Member.MemberSignUpDto;
import Fasion.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Slf4j
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원 가입
    public String registerMember(MemberSignUpDto dto) {
        log.info("registerMember(dto={})" , dto);

        Member entity = Member.builder()
                .userId(dto.getUserId())
                .password(passwordEncoder.encode(dto.getPassword()))
                .niceName(dto.getNiceName())
                .email(dto.getEmail())
                .birthday(dto.getBirthday())
                .gender(dto.getGender())
                .build();

        log.info("save 전: entity={}", entity);

        memberRepository.save(entity);
        log.info("save 후: entoty={}" , entity);

        return entity.getUserId(); // DBd에 저장된 ID(고유키)를 리턴.
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("logUserByUsernmae(username={})" , username);

        // DB에서 username으로 사용자 정보 검색 (select)
        UserDetails user = memberRepository.findByUsername(username);

        if(user != null) {
            return user;
        }
        throw new UsernameNotFoundException(username + "not Found");
    }

}
