package uos.capstone.backend.place.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import uos.capstone.backend.place.domain.PlaceReview;
import uos.capstone.backend.place.dto.response.PlaceReviewResponse;

@Mapper(componentModel = "spring")
public interface PlaceReviewMapper {

	PlaceReviewMapper INSTANCE = Mappers.getMapper(PlaceReviewMapper.class);

	@Mapping(source = "id", target="reviewId")
	@Mapping(source = "placeReview.user.id", target="userId")
	@Mapping(source = "placeReview.user.name", target="name")
	PlaceReviewResponse toDto(PlaceReview placeReview);
}
