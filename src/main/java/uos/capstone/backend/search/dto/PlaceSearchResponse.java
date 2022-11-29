package uos.capstone.backend.search.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PlaceSearchResponse {
	private Long placeId;
	private String title;
	private String address;
	private String thumbnail;
	private Integer likeCount;

	@QueryProjection
	public PlaceSearchResponse(Long placeId, String title, String address, String thumbnail, Integer likeCount) {
		this.placeId = placeId;
		this.title = title;
		this.address = address;
		this.thumbnail = thumbnail;
		this.likeCount = likeCount;
	}
}
