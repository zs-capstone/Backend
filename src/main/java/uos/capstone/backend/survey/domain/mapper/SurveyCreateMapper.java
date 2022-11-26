package uos.capstone.backend.survey.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import uos.capstone.backend.place.domain.Place;
import uos.capstone.backend.survey.domain.Survey;
import uos.capstone.backend.survey.dto.request.SurveyCreateRequest;
import uos.capstone.backend.survey.dto.response.SurveyResponse;
import uos.capstone.backend.user.domain.User;

@Mapper(componentModel = "spring")
public interface SurveyCreateMapper {

	SurveyCreateMapper INSTANCE = Mappers.getMapper(SurveyCreateMapper.class);

	Survey toEntity(Place place, Double rate, User user);
}
