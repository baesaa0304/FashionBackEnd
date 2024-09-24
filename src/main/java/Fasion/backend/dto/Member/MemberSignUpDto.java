package Fasion.backend.dto.Member;


import Fasion.backend.domain.Member.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberSignUpDto {
    private String userId;
    private String password;
    private String nickName;
    private String email;
    private LocalDate birthday;
    private Gender gender;
}
