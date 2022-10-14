package uos.capstone.backend.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter @Setter
@Schema(description = "회원가입 양식")
@NoArgsConstructor @AllArgsConstructor @Builder
public class JoinDto {

    @NotNull
    @Schema(example = "김철수")
    private String username;

    @NotNull
    @Email
    @Schema(example = "1@example.com")
    private String email;

    @NotNull
    @Schema(example = "12345678")
    private String password;

    @NotNull
    @Schema(example = "노예1")
    private String nickname;
}
