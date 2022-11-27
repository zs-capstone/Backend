package uos.capstone.backend.survey.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.capstone.backend.common.domain.BaseEntity;
import uos.capstone.backend.place.domain.Place;
import uos.capstone.backend.user.domain.User;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Survey extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="placeId")
	private Place place;

	@Column(nullable = false)
	private Double rate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userId")
	private User user;

}
