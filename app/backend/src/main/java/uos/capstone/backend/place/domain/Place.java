package uos.capstone.backend.place.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.locationtech.jts.geom.Point;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.capstone.backend.common.domain.BaseEntity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Place extends BaseEntity {

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String address;


	private String introduction;

	@Column(nullable = false)
	private Point point;


	private String phone;


	private Long popularity;

	@Column(nullable = false)
	private String type;


	private String tag;

	@Column(nullable = false)
	private String thumbnail;

	@Column(nullable = false)
	private Long likeCount;
}
