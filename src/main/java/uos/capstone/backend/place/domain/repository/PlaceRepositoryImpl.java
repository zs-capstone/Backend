package uos.capstone.backend.place.domain.repository;

import static uos.capstone.backend.place.domain.QPlace.place;

import static uos.capstone.backend.place.domain.QPlaceLike.placeLike;
import static uos.capstone.backend.place.domain.QPlaceReview.placeReview;

import java.util.List;
import java.util.Optional;

import javax.persistence.Column;

import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import uos.capstone.backend.place.dto.response.PlaceResponse;
import uos.capstone.backend.place.dto.response.PlaceSimpleResponse;
import uos.capstone.backend.place.dto.response.QPlaceResponse;
import uos.capstone.backend.place.dto.response.QPlaceSimpleResponse;
import uos.capstone.backend.search.dto.PlaceSearchResponse;
import uos.capstone.backend.search.dto.QPlaceSearchResponse;

@RequiredArgsConstructor
public class PlaceRepositoryImpl implements PlaceRepositoryCustom{

	private final JPAQueryFactory queryFactory;

	@Override
	public Optional<PlaceResponse> findOnePlace(Long id) {
		Optional<PlaceResponse> content = Optional.ofNullable(
			queryFactory.select(
				new QPlaceResponse(
					place.id,
					place.title,
					place.introduction,
					place.point,
					place.phone,
					place.popularity,
					place.type,
					place.address,
					place.thumbnail,
					place.tag,
					place.placeLikeList.size(),
					place.placeReviewList.size()
				))
			.from(place).where(place.id.eq(id))
			.fetchOne());

		if (content.isEmpty()) {
			return Optional.empty();
		}

		return content;
	}

	@Override
	public Slice<PlaceSimpleResponse> findAllOrderByPage(Pageable pageable) {
		List<PlaceSimpleResponse> content = queryFactory.select(
				new QPlaceSimpleResponse(
					place.id,
					place.title,
					place.address,
					place.thumbnail,
					place.tag,
					place.placeLikeList.size()
				))
			.from(place)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize()+1)
			.orderBy(placeSort(pageable))
			.fetch();

		boolean hasNext = false;
		if (content.size() > pageable.getPageSize()) {
			content.remove(pageable.getPageSize());
			hasNext = true;
		}
		return new SliceImpl<>(content, pageable, hasNext);
	}

	public Slice<PlaceSimpleResponse> findAllByUser(Long userId, Pageable pageable) {
		List<Long> plist = queryFactory.select(placeLike.place.id).distinct()
			.from(placeLike)
			.where(placeLike.user.id.eq(userId))
			.fetch();

		List<PlaceSimpleResponse> content = queryFactory.select(
				new QPlaceSimpleResponse(
					place.id,
					place.title,
					place.address,
					place.thumbnail,
					place.tag,
					place.placeLikeList.size()
				))
			.from(place)
			.where(place.id.in(plist))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize()+1)
			.orderBy(placeSort(pageable))
			.fetch();

		boolean hasNext = false;
		if (content.size() > pageable.getPageSize()) {
			content.remove(pageable.getPageSize());
			hasNext = true;
		}
		return new SliceImpl<>(content, pageable, hasNext);
	}

	private OrderSpecifier<?> placeSort(Pageable page) {
		//??????????????? ????????? Pageable ????????? ???????????? null ??? ??????
		if (!page.getSort().isEmpty()) {
			//???????????? ?????? ????????? for ???????????? ?????? ????????????
			for (Sort.Order order : page.getSort()) {
				// ??????????????? ????????? DESC or ASC ??? ????????????.
				Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
				// ??????????????? ????????? ?????? ????????? ????????? ????????? ?????? ???????????? ???????????? ??????.
				switch (order.getProperty()){
					case "title":
						return new OrderSpecifier(direction, place.title);
					case "popularity":
						return new OrderSpecifier(direction, place.popularity);
				}
			}
		}
		return null;
	}

	@Override
	public Slice<PlaceSearchResponse> findAllPlaceBySearch(String query, Pageable pageable) {
		List<PlaceSearchResponse> content = queryFactory.select(
				new QPlaceSearchResponse(
					place.id,
					place.title,
					place.address,
					place.thumbnail,
					place.placeLikeList.size()
				))
			.from(place)
			.where(place.title.contains(query))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize()+1)
			.orderBy(placeSort(pageable))
			.fetch();

		boolean hasNext = false;
		if (content.size() > pageable.getPageSize()) {
			content.remove(pageable.getPageSize());
			hasNext = true;
		}
		return new SliceImpl<>(content, pageable, hasNext);
	}
}
