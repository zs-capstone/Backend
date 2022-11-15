package uos.capstone.backend.search.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlaceSearchResponse {
	private Integer placeId;
	private String title;
	private String thumbnail;
	private String address;
	private Integer likeCount;
}
