package uos.capstone.backend.note.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;
import uos.capstone.backend.note.domain.NoteRegion;
import uos.capstone.backend.note.domain.NoteTheme;

@Getter
@Builder
public class NoteResponse {

	private String title;

	private Date dayStart;

	private Date dayEnd;

	private Integer adult;

	private Integer child;

	private Integer animal;

	private List<NoteRegion> region;

	private List<NoteTheme> theme;

}
