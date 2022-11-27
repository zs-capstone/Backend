package uos.capstone.backend.note.dto.response;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class RecommendInfoResponse {

	private Integer day;
	private Long placeId;
	private Boolean isUserPick;

	@QueryProjection
	public RecommendInfoResponse(Integer day, Long placeId, Boolean isUserPick) {
		this.day = day;
		this.placeId = placeId;
		this.isUserPick = isUserPick;
	}

}
