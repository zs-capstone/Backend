package uos.capstone.backend.note.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.capstone.backend.common.domain.BaseEntity;
import uos.capstone.backend.note.dto.request.NoteCreateRequest;
import uos.capstone.backend.note.dto.request.NoteUpdateRequest;
import uos.capstone.backend.user.domain.User;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Note extends BaseEntity {
	@Column(nullable = false)
	private String title;

	@Column(columnDefinition = "DATETIME(6)", nullable = false)
	private LocalDate dayStart;

	@Column(columnDefinition = "DATETIME(6)", nullable = false)
	private LocalDate dayEnd;

	@Column(nullable = false)
	private Integer adult;

	@Column(nullable = false)
	private Integer child;

	@Column(nullable = false)
	private Integer animal;

	@Column(nullable = false)
	private Boolean publicShare;

	@Column(nullable = false)
	private Integer maxPlacePerDay;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userId")
	private User user;

	@Builder
	public Note(NoteCreateRequest noteCreateRequest, User user) {
		this.title = noteCreateRequest.getTitle();
		this.dayStart = noteCreateRequest.getDayStart();
		this.dayEnd = noteCreateRequest.getDayEnd();
		this.adult = noteCreateRequest.getAdult();
		this.child = noteCreateRequest.getChild();
		this.animal = noteCreateRequest.getAnimal();
		this.publicShare = noteCreateRequest.getPublicShare();
		this.maxPlacePerDay = noteCreateRequest.getMaxPlacePerDay();
		this.user = user;
	}

	public void updateNote(NoteUpdateRequest noteUpdateRequest) {

		if (noteUpdateRequest.getTitle() != null) {
			this.title = noteUpdateRequest.getTitle();
		}
		if (noteUpdateRequest.getDayStart() != null) {
			this.dayStart = noteUpdateRequest.getDayStart();
		}
		if (noteUpdateRequest.getDayEnd() != null) {
			this.dayEnd = noteUpdateRequest.getDayEnd();
		}
		if (noteUpdateRequest.getAdult() != null) {
			this.adult = noteUpdateRequest.getAdult();
		}
		if (noteUpdateRequest.getChild() != null) {
			this.child = noteUpdateRequest.getChild();
		}
		if (noteUpdateRequest.getAnimal() != null) {
			this.animal = noteUpdateRequest.getAnimal();
		}
		if (noteUpdateRequest.getPublicShare() != null) {
			this.publicShare = noteUpdateRequest.getPublicShare();
		}
		if (noteUpdateRequest.getMaxPlacePerDay() != null) {
			this.maxPlacePerDay = noteUpdateRequest.getMaxPlacePerDay();
		}

	}

}
