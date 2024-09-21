package Fasion.backend.domain.Member;

import Fasion.backend.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;




@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "USER")
public class Member extends BaseTimeEntity implements UserDetails {
    @Id
    private String userId;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NICENAME", nullable = false)
    private String niceName;

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

    @Builder
    private Member(String userId, String password, String niceName, String email, LocalDate birthday, Gender gender) {
        this.userId = userId;
        this.password = password;
        this.niceName = niceName;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
        this.role = Role.USER; // 기본 값
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(role.getKey()));
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
