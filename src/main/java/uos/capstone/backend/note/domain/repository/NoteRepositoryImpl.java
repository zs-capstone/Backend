package uos.capstone.backend.note.domain.repository;

import static uos.capstone.backend.note.domain.QNote.note;
import static uos.capstone.backend.note.domain.QRecommend.recommend;
import static uos.capstone.backend.place.domain.QPlace.place;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import uos.capstone.backend.note.dto.response.NoteInfoResponse;
import uos.capstone.backend.note.dto.response.QNoteInfoResponse;
import uos.capstone.backend.note.dto.response.QRecommendInfoResponse;
import uos.capstone.backend.note.dto.response.RecommendInfoResponse;
import uos.capstone.backend.search.dto.NoteSearchResponse;
import uos.capstone.backend.search.dto.QNoteSearchResponse;

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

	@Override
	public Slice<NoteSearchResponse> findAllNoteBySearch(String query, Pageable pageable) {
		List<NoteSearchResponse> content = queryFactory.select(
				new QNoteSearchResponse(
					note.id,
					note.title,
					note.dayStart,
					note.dayEnd,
					note.adult,
					note.child,
					note.animal,
					note.publicShare,
					note.maxPlacePerDay,
					note.user.nickname
				))
			.from(note)
			.where(note.title.contains(query))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize()+1)
			.orderBy(NoteSort(pageable))
			.fetch();

		boolean hasNext = false;
		if (content.size() > pageable.getPageSize()) {
			content.remove(pageable.getPageSize());
			hasNext = true;
		}

		return new SliceImpl<>(content, pageable, hasNext);
	}

	private OrderSpecifier<?> NoteSort(Pageable page) {
		//??????????????? ????????? Pageable ????????? ???????????? null ??? ??????
		if (!page.getSort().isEmpty()) {
			//???????????? ?????? ????????? for ???????????? ?????? ????????????
			for (Sort.Order order : page.getSort()) {
				// ??????????????? ????????? DESC or ASC ??? ????????????.
				Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
				// ??????????????? ????????? ?????? ????????? ????????? ????????? ?????? ???????????? ???????????? ??????.
				switch (order.getProperty()){
					case "title":
						return new OrderSpecifier(direction, note.title);
					case "createdAt":
						return new OrderSpecifier(direction, place.createdAt);
				}
			}
		}
		return null;
	}
}
