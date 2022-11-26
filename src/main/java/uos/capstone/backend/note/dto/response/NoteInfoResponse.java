// package uos.capstone.backend.note.dto.response;
//
// import java.util.Date;
// import java.util.List;
//
// import com.querydsl.core.annotations.QueryProjection;
//
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;
// import uos.capstone.backend.note.domain.Recommend;
//
// @Getter
// @Setter
// @NoArgsConstructor
// public class NoteInfoResponse {
//
// 	private String title;
//
// 	private Date dayStart;
//
// 	private Date dayEnd;
//
// 	private Integer adult;
//
// 	private Integer child;
//
// 	private Integer animal;
//
// 	private List<Recommend> recommendList;
//
// 	@QueryProjection
// 	public NoteInfoResponse(String title, Date dayStart, Date dayEnd, Integer adult, Integer child, Integer animal) {
// 		this.title = title;
// 		this.dayStart = dayStart;
// 		this.dayEnd = dayEnd;
// 		this.adult = adult;
// 		this.child = child;
// 		this.animal = animal;
// 	}
// }
//
