package uos.capstone.backend.note.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NoteCreateRequest {
	@NotBlank(message = "제목은 필수입니다.")
	private String title;

	@NotNull(message = "날짜는 필수입니다.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dayStart;

	@NotNull(message = "날짜는 필수입니다.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dayEnd;

	@NotNull(message = "인원 수는 필수입니다.")
	private Integer adult;

	@NotNull(message = "인원 수는 필수입니다.")
	private Integer child;

	@NotNull(message = "반려동물 수는 필수입니다.")
	private Integer animal;

	@NotNull(message = "장소는 필수입니다.")
	private List<Long> placeList;

	@NotNull(message = "공개여부는 필수입니다.")
	private Boolean publicShare;

	@NotNull(message = "하루 당 추천받을 장소 개수는 필수입니다.")
	private Integer maxPlacePerDay;
}

