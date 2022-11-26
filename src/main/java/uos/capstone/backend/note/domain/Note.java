// package uos.capstone.backend.note.domain;
//
// import java.util.Date;
// import java.util.List;
//
// import javax.persistence.CascadeType;
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.FetchType;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;
// import javax.persistence.OneToOne;
// import javax.validation.constraints.NotBlank;
//
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import uos.capstone.backend.common.domain.BaseEntity;
// import uos.capstone.backend.note.dto.request.NoteCreateRequest;
// import uos.capstone.backend.note.dto.request.NoteUpdateRequest;
// import uos.capstone.backend.place.domain.Place;
// import uos.capstone.backend.user.domain.User;
// import uos.capstone.backend.user.dto.request.UserUpdateRequest;
//
// @Entity
// @NoArgsConstructor
// @AllArgsConstructor
// @Getter
// public class Note extends BaseEntity {
// 	@Column(nullable = false)
// 	private String title;
//
// 	@Column(nullable = false)
// 	private Date dayStart;
//
// 	@Column(nullable = false)
// 	private Date dayEnd;
//
// 	@Column(nullable = false)
// 	private Integer adult;
//
// 	@Column(nullable = false)
// 	private Integer child;
//
// 	@Column(nullable = false)
// 	private Integer animal;
//
// 	// @OneToMany(mappedBy = "note", orphanRemoval = true, cascade = {CascadeType.ALL})
// 	// private List<NoteRegion> region;
// 	//
// 	// @OneToMany(mappedBy = "note",orphanRemoval = true, cascade = {CascadeType.ALL})
// 	// private List<NoteTheme> theme;
//
// 	@OneToMany(mappedBy = "note", orphanRemoval = true, cascade = {CascadeType.ALL})
// 	private List<Place> placeList;
//
// 	@Column(nullable = false)
// 	private Boolean public_share;
//
// 	@OneToMany(mappedBy = "note", orphanRemoval = true, cascade = {CascadeType.ALL})
// 	private List<Recommend> recommendList;
//
// 	@ManyToOne(fetch = FetchType.LAZY)
// 	@JoinColumn(name="userId")
// 	private User user;
//
// 	@Builder
// 	public Note(NoteCreateRequest noteCreateRequest, User user, List<Place> placeList, List<Recommend> recommendList) {
// 		this.title = noteCreateRequest.getTitle();
// 		this.dayStart = noteCreateRequest.getDayStart();
// 		this.dayEnd = noteCreateRequest.getDayEnd();
// 		this.adult = noteCreateRequest.getAdult();
// 		this.child = noteCreateRequest.getChild();
// 		this.animal = noteCreateRequest.getAnimal();
// 		this.placeList = placeList;
// 		this.public_share = noteCreateRequest.getPublic_share();
// 		this.user = user;
// 		this.recommendList = recommendList;
// 	}
//
// 	public void updateNote(NoteUpdateRequest noteUpdateRequest) {
//
// 		if (noteUpdateRequest.getTitle() != null) {
// 			this.title = noteUpdateRequest.getTitle();
// 		}
// 		if (noteUpdateRequest.getDayStart() != null) {
// 			this.dayStart = noteUpdateRequest.getDayStart();
// 		}
// 		if (noteUpdateRequest.getDayEnd() != null) {
// 			this.dayEnd = noteUpdateRequest.getDayEnd();
// 		}
// 		if (noteUpdateRequest.getAdult() != null) {
// 			this.adult = noteUpdateRequest.getAdult();
// 		}
// 		if (noteUpdateRequest.getChild() != null) {
// 			this.child = noteUpdateRequest.getChild();
// 		}
// 		if (noteUpdateRequest.getAnimal() != null) {
// 			this.animal = noteUpdateRequest.getAnimal();
// 		}
// 		if (noteUpdateRequest.getPublic_share() != null) {
// 			this.public_share = noteUpdateRequest.getPublic_share();
// 		}
//
// 	}
//
// 	public void updateNotePlace(List<Place> placeList) {
// 		this.placeList = placeList;
// 	}
//
// }
