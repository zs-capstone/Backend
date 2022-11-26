package uos.capstone.backend.note.dto.request;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NoteUpdateRequest {

	private String title;

	private Date dayStart;

	private Date dayEnd;

	private Integer adult;

	private Integer child;

	private Integer animal;

	private Boolean public_share;

}
