package uos.capstone.backend.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class JoinDto {

    @NotNull
    @Schema(defaultValue = "김철수")
    private String username;

    @NotNull
    @Email
    @Schema(defaultValue = "1@example.com")
    private String email;

    @NotNull
    @Schema(defaultValue = "12345678")
    private String password;

    @NotNull
    @Schema(defaultValue = "노예1")
    private String nickname;
}
