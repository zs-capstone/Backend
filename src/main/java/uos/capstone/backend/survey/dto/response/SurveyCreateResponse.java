package uos.capstone.backend.survey.dto.response;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SurveyCreateResponse {

	private Long id;

	private String title;

	private String introduction;

	private String imageUrl;

	private String type;

	private String tag;

	@QueryProjection
	public SurveyCreateResponse(Long id, String title, String introduction, String imageUrl, String type, String tag) {
		this.id = id;
		this.title = title;
		this.introduction = introduction;
		this.imageUrl = imageUrl;
		this.type = type;
		this.tag = tag;
	}
}
