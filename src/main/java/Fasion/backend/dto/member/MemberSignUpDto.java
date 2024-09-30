package Fasion.backend.dto.member;


import Fasion.backend.domain.member.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberSignUpDto {
    private String memberId;
    private String password;
    private String nickName;
    private String email;
    private LocalDate birthday;
    private Gender gender;
}
