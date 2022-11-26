package uos.capstone.backend.survey.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SurveyResponse {

	private Long placeId;
	private String imageUrl;
	private String title;
	private String address;
	private String tag;
	private String introduction;

}
