package uos.capstone.backend.place.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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

	@OneToMany(mappedBy = "place", orphanRemoval = true)
	private List<PlaceLike> placeLikeList;

	@OneToMany(mappedBy = "place", orphanRemoval = true)
	private List<PlaceReview> placeReviewList;

}
