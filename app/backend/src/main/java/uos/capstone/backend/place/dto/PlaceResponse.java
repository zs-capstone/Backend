package uos.capstone.backend.place.dto;

import org.locationtech.jts.geom.Point;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlaceResponse {

	private Long placeId;
	private String title;
	private String address;
	private String imageUrl;
	private String tag;
	private String introduction;

}
