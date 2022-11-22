package uos.capstone.backend.place.dto.response;

import org.locationtech.jts.geom.Point;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PlaceResponse {
	private Long placeId;
	private String title;
	private String introduction;
	private Double longitude;
	private Double latitude;
	private String phone;
	private Long popularity;
	private String type;
	private String address;
	private String imageUrl;
	private String tag;
	private Integer likeCount;
	private Integer reviewCount;

	@QueryProjection
	public PlaceResponse(Long placeId, String title, String introduction, Point point,
		String phone,
		Long popularity, String type, String address, String imageUrl,
		String tag, Integer likeCount, Integer reviewCount) {
		this.placeId = placeId;
		this.title = title;
		this.introduction = introduction;
		this.longitude = point.getX();
		this.latitude = point.getY();
		this.phone = phone;
		this.popularity = popularity;
		this.type = type;
		this.address = address;
		this.imageUrl = imageUrl;
		this.tag = tag;
		this.likeCount = likeCount;
		this.reviewCount = reviewCount;
	}
}
