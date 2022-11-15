package uos.capstone.backend.place.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import uos.capstone.backend.place.domain.Place;
import uos.capstone.backend.place.dto.PlaceResponse;

@Mapper(componentModel = "spring")
public interface PlaceResponseMapper {

	PlaceResponseMapper INSTANCE = Mappers.getMapper(PlaceResponseMapper.class);

	@Mapping(source = "id", target="placeId")
	@Mapping(source="thumbnail",target="imageUrl")
	PlaceResponse toDto(Place place);

}
