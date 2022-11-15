package uos.capstone.backend.place.dto;

import org.locationtech.jts.geom.Point;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PlaceResponse {

	private Long placeId;
	private String title;
	private String address;
	private String imageUrl;
	private String tag;
	private String introduction;

	@QueryProjection
	public PlaceResponse(Long placeId, String title, String address, String imageUrl, String tag, String introduction) {
		this.placeId = placeId;
		this.title = title;
		this.address = address;
		this.imageUrl = imageUrl;
		this.tag = tag;
		this.introduction = introduction;
	}
}
