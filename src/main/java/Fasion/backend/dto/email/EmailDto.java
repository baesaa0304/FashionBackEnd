package Fasion.backend.dto.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {
    @Email
    @NotEmpty(message = "이메일을 입력해 주세요")
    private String email;
}

