package uos.capstone.backend.note.domain.repository;

import static uos.capstone.backend.note.domain.QNote.note;
import static uos.capstone.backend.note.domain.QRecommend.recommend;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import uos.capstone.backend.note.dto.response.NoteInfoResponse;
import uos.capstone.backend.note.dto.response.QNoteInfoResponse;
import uos.capstone.backend.note.dto.response.QRecommendInfoResponse;
import uos.capstone.backend.note.dto.response.RecommendInfoResponse;

@RequiredArgsConstructor
public class NoteRepositoryImpl implements NoteRepositoryCustom{

	private final JPAQueryFactory queryFactory;

	@Override
	public NoteInfoResponse findOneNoteById(Long noteId) {
		NoteInfoResponse noteInfoResponse = queryFactory.select(
			new QNoteInfoResponse(
				note.title,
				note.dayStart,
				note.dayEnd,
				note.adult,
				note.child,
				note.animal,
				note.maxPlacePerDay
			))
			.from(note)
			.where(note.id.eq(noteId))
			.fetchOne();

		List<RecommendInfoResponse> infoList = queryFactory.select(
			new QRecommendInfoResponse(
				recommend.day,
				recommend.place.id,
				recommend.isUserPick
			))
			.from(recommend)
			.where(recommend.note.id.eq(noteId))
			.fetch();

		noteInfoResponse.setRecommendList(infoList);

		return noteInfoResponse;
	}
}
