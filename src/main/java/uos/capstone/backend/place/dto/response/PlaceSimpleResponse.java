package uos.capstone.backend.place.dto.response;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;

@Getter
public class PlaceSimpleResponse {
	private Long placeid;

	private String title;

	private String imageUrl;

	private String address;

	private String tag;

	private Integer likeCount;

	@QueryProjection
	public PlaceSimpleResponse(Long placeid, String title, String imageUrl, String address, String tag,
		Integer likeCount) {
		this.placeid = placeid;
		this.title = title;
		this.imageUrl = imageUrl;
		this.address = address;
		this.tag = tag;
		this.likeCount = likeCount;
	}
}
