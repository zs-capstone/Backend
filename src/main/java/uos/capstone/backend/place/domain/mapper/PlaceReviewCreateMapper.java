package uos.capstone.backend.place.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import uos.capstone.backend.place.domain.Place;
import uos.capstone.backend.place.domain.PlaceReview;
import uos.capstone.backend.place.dto.request.PlaceReviewCreateRequest;
import uos.capstone.backend.place.dto.response.PlaceReviewResponse;
import uos.capstone.backend.user.domain.User;

@Mapper(componentModel = "spring")
public interface PlaceReviewCreateMapper {
	PlaceReviewCreateMapper INSTANCE = Mappers.getMapper(PlaceReviewCreateMapper.class);

	PlaceReview toEntity(User user, Place place,PlaceReviewCreateRequest placeReviewCreateRequest);
}