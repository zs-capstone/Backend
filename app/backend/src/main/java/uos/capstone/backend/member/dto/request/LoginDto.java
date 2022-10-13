package uos.capstone.backend.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class LoginDto {

    @NotNull
    @Email
    @Schema(defaultValue = "idwooin@naver.com")
    private String email;

    @NotNull
    @Schema(defaultValue = "12345678")
    private String password;
}
