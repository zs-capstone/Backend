package uos.capstone.backend.search.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSearchResponse {
	private Long userId;
	private String profileImg;
	private String nickname;
}
