package uos.capstone.backend.place.domain.repository;

import static uos.capstone.backend.place.domain.QPlace.place;

import static uos.capstone.backend.place.domain.QPlaceLike.placeLike;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import uos.capstone.backend.place.dto.PlaceResponse;
import uos.capstone.backend.place.dto.QPlaceResponse;

@RequiredArgsConstructor
public class PlaceRepositoryImpl implements PlaceRepositoryCustom{

	private final JPAQueryFactory queryFactory;
	@Override
	public Slice<PlaceResponse> findAllByUser(Long userId, Pageable pageable) {
		List<PlaceResponse> content = queryFactory.select(
			new QPlaceResponse(
				place.id,
				place.title,
				place.address,
				place.thumbnail,
				place.tag,
				place.introduction
			))
			.from(placeLike)
			.innerJoin(placeLike.place,place)
			.where(placeLike.user.id.eq(userId))
			.fetch();

		boolean hasNext = false;
		if (content.size() > pageable.getPageSize()) {
			content.remove(pageable.getPageSize());
			hasNext = true;
		}
		return new SliceImpl<>(content, pageable, hasNext);
	}
}
