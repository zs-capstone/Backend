package uos.capstone.backend.survey.domain.repository;

import static uos.capstone.backend.place.domain.QPlace.place;
import static uos.capstone.backend.survey.domain.QSurvey.survey;

import java.util.List;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
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
	public List<SurveyCreateResponse> findRandom10Place() {
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
			.orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
			.limit(10)
			.fetch();

		if (content.size() != 10) {
			throw new NotEnoughSurveyQueryException();
		}

		return content;
	}

}
