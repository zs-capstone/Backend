package uos.capstone.backend.search.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import uos.capstone.backend.place.domain.Place;
import uos.capstone.backend.search.dto.PlaceSearchResponse;

@Mapper(componentModel = "spring")
public interface PlaceSearchResponseMapper {

	PlaceSearchResponseMapper INSTANCE = Mappers.getMapper(PlaceSearchResponseMapper.class);

	@Mapping(source="id",target = "placeId")
	PlaceSearchResponse toDto(Place place);

}
