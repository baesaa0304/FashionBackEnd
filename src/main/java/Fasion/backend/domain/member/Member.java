package Fasion.backend.domain.member;

import Fasion.backend.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@NoArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name = "USER")
public class Member extends BaseTimeEntity implements UserDetails {
    @Id
    private String userId;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NICKNAME", nullable = false)
    private String nickName;

    @Column(name = "BIRTHDAY", nullable = false)
    private LocalDate birthday;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private Role role;

    private boolean isEmailVerified;

    @Builder
    private Member(String userId, String password, String nickName, String email, LocalDate birthday, Gender gender) {
        this.userId = userId;
        this.password = password;
        this.nickName = nickName;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
        this.role = Role.USER; // 기본 값
    }

    // 사용자의 권한 목록을 반환(사용자 인지 관리자 인지)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getKey()));
    }
    // 사용자 id 반환 (고유값)
    @Override
    public String getUsername() {
        return this.userId;
    }

    // 사용자의 계정이 만료되었는지 여부
    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정이 만료되지 않았음을 의미
    }

    // 계정이 잠겼는지 여부
    @Override
    public boolean isAccountNonLocked() {
        return true; // 잠금 X
    }

    //비밀번호 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 만료 X
    }
    //계정 로그인 상태 활성화 상태인지
    @Override
    public boolean isEnabled() {
        return true; //계정이 활성화된 상태
    }
}
