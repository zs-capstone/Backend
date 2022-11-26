// package uos.capstone.backend.note.domain;
//
// import java.util.List;
//
// import javax.persistence.CascadeType;
// import javax.persistence.Entity;
// import javax.persistence.FetchType;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;
//
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import uos.capstone.backend.common.domain.BaseEntity;
// import uos.capstone.backend.place.domain.Place;
//
// @Entity
// @NoArgsConstructor
// @AllArgsConstructor
// @Getter
// @Builder
// public class Recommend extends BaseEntity {
//
// 	private Integer day;
//
// 	@ManyToOne(fetch = FetchType.LAZY)
// 	@JoinColumn(name="noteId")
// 	private Note note;
//
// 	@ManyToOne(fetch = FetchType.LAZY)
// 	@JoinColumn(name="placeId")
// 	private Place place;
//
// }
