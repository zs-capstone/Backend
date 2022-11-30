package uos.capstone.backend.user.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserNicknameRequest {
	@NotBlank(message = "닉네임은 빈 칸일 수 없습니다")
	@Size(min=3, max=15, message = "닉네임은 3자 이상 15자 이하여야 합니다")
	private String nickname;
}
