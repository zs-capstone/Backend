package uos.capstone.backend.note.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;
import uos.capstone.backend.note.domain.NotePlace;
import uos.capstone.backend.note.domain.NoteRegion;
import uos.capstone.backend.note.domain.NoteTheme;

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

	@NotBlank(message = "지역 선택은 필수입니다.")
	private List<NoteRegion> region;

	@NotBlank(message = "테마는 필수입니다.")
	private List<NoteTheme> theme;

	@NotBlank(message = "장소는 필수입니다.")
	private List<NotePlace> place;

	@NotBlank(message = "공개여부는 필수입니다.")
	private Boolean public_share;
}
