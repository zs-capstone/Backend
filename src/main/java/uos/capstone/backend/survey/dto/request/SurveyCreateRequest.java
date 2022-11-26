package uos.capstone.backend.survey.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class SurveyCreateRequest {

	@NotNull
	private List<SurveyInitRating> surveyInitRatingList;

}
