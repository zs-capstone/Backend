package uos.capstone.backend.survey.domain.repository;

import static uos.capstone.backend.place.domain.QPlace.place;
import static uos.capstone.backend.place.domain.QPlaceReview.placeReview;
import static uos.capstone.backend.survey.domain.QSurvey.survey;

import java.util.List;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import uos.capstone.backend.place.dto.response.PlaceSimpleResponse;
import uos.capstone.backend.place.dto.response.QPlaceSimpleResponse;
import uos.capstone.backend.survey.dto.response.QSurveyCreateResponse;
import uos.capstone.backend.survey.dto.response.SurveyCreateResponse;
import uos.capstone.backend.survey.exception.NotEnoughSurveyQueryException;

@RequiredArgsConstructor
public class SurveyRepositoryCustomImpl implements SurveyRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<SurveyCreateResponse> findRandom20Place() {
		List<SurveyCreateResponse> content = queryFactory.select(
				new QSurveyCreateResponse(
					place.id,
					place.title,
					place.introduction,
					place.thumbnail,
					place.type,
					place.tag
				))
			.from(place)
			.where(
				place.id.in(
					JPAExpressions.select(placeReview.place.id)
						.from(placeReview)
						.groupBy(placeReview.place)
						.having(placeReview.id.count().gt(19))
				)
			)
			.orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
			.limit(20)
			.fetch();

		if (content.size() != 20) {
			throw new NotEnoughSurveyQueryException();
		}

		return content;
	}

}
