package uos.capstone.backend.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
	private Long id;
	private String email;
	private String name;
	private String nickname;
	private String phone;
	private String region;
	private Boolean isSurvey;
}
