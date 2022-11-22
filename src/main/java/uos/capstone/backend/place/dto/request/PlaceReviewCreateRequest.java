package uos.capstone.backend.place.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlaceReviewCreateRequest {

	@NotNull(message = "별점이 존재해야합니다")
	private Double rate;

	@NotBlank(message = "내용은 빈 칸일 수 없습니다")
	@Size(min=10,message="리뷰 내용은 10자 이상 작성해야합니다")
	private String content;

}
