package uos.capstone.backend.user.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEmailRequest {
	@NotBlank(message = "이메일은 빈 칸일 수 없습니다")
	@Email(message = "이메일 형식이 맞지 않습니다")
	private String email;
}
