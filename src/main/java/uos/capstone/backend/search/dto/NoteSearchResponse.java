package uos.capstone.backend.search.dto;

import java.time.LocalDate;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NoteSearchResponse {
	private Long noteId;
	private String title;
	private LocalDate dayStart;
	private LocalDate dayEnd;
	private Integer adult;
	private Integer child;
	private Integer animal;
	private Boolean publicShare;
	private Integer maxPlacePerDay;
	private String name;

	@QueryProjection
	public NoteSearchResponse(Long noteId, String title, LocalDate dayStart, LocalDate dayEnd, Integer adult,
		Integer child,
		Integer animal, Boolean publicShare, Integer maxPlacePerDay, String name) {
		this.noteId = noteId;
		this.title = title;
		this.dayStart = dayStart;
		this.dayEnd = dayEnd;
		this.adult = adult;
		this.child = child;
		this.animal = animal;
		this.publicShare = publicShare;
		this.maxPlacePerDay = maxPlacePerDay;
		this.name = name;
	}
}
