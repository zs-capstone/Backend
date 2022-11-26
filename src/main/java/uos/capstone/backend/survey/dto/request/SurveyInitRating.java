package uos.capstone.backend.survey.dto.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class SurveyInitRating {

	@NotNull
	private Long placeId;

	@NotNull
	private Double rate;

}
