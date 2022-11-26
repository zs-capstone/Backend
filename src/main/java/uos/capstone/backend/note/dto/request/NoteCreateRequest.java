package uos.capstone.backend.note.dto.request;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NoteCreateRequest {
	@NotBlank(message = "제목은 필수입니다.")
	private String title;

	@NotBlank(message = "날짜는 필수입니다.")
	private Date dayStart;

	@NotBlank(message = "날짜는 필수입니다.")
	private Date dayEnd;

	@NotBlank(message = "인원 수는 필수입니다.")
	private Integer adult;

	@NotBlank(message = "인원 수는 필수입니다.")
	private Integer child;

	@NotBlank(message = "반려동물 수는 필수입니다.")
	private Integer animal;

	@NotBlank(message = "장소는 필수입니다.")
	private List<Long> placeList;

	@NotBlank(message = "공개여부는 필수입니다.")
	private Boolean public_share;
}

