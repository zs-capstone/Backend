package uos.capstone.backend.note.dto.response;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uos.capstone.backend.note.domain.Recommend;
import uos.capstone.backend.place.domain.Place;

@Getter
@Setter
@NoArgsConstructor
public class NoteInfoResponse {

	private String title;

	private LocalDate dayStart;

	private LocalDate dayEnd;

	private Integer adult;

	private Integer child;

	private Integer animal;

	private Integer maxPlacePerDay;

	private List<Place> placeList;

	private List<Recommend> recommendList;

	@QueryProjection
	public NoteInfoResponse(String title, LocalDate dayStart, LocalDate dayEnd,
		Integer adult, Integer child, Integer animal, Integer maxPlacePerDay) {
		this.title = title;
		this.dayStart = dayStart;
		this.dayEnd = dayEnd;
		this.adult = adult;
		this.child = child;
		this.animal = animal;
		this.maxPlacePerDay = maxPlacePerDay;
	}
}

