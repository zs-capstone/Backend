package uos.capstone.backend.survey.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import uos.capstone.backend.place.domain.Place;
import uos.capstone.backend.survey.dto.response.SurveyResponse;

@Mapper(componentModel = "spring")
public interface SurveyMapper {

	SurveyMapper INSTANCE = Mappers.getMapper(SurveyMapper.class);

	@Mapping(source="id", target="placeId")
	@Mapping(source="thumbnail", target="imageUrl")
	SurveyResponse toDto(Place place);
}
