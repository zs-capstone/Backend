package uos.capstone.backend.note.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NoteUpdateRequest {

	private String title;

	private LocalDate dayStart;

	private LocalDate dayEnd;

	private Integer adult;

	private Integer child;

	private Integer animal;

	private Boolean publicShare;

	private Integer maxPlacePerDay;

}
