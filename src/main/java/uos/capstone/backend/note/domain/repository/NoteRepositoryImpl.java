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
		//서비스에서 보내준 Pageable 객체에 정렬조건 null 값 체크
		if (!page.getSort().isEmpty()) {
			//정렬값이 들어 있으면 for 사용하여 값을 가져온다
			for (Sort.Order order : page.getSort()) {
				// 서비스에서 넣어준 DESC or ASC 를 가져온다.
				Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
				// 서비스에서 넣어준 정렬 조건을 스위치 케이스 문을 활용하여 셋팅하여 준다.
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
