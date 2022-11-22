package uos.capstone.backend.place.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlaceReviewResponse {
	private Long reviewId;
	private Long userId;
	private String name;
	private Double rate;
	private String content;
}
