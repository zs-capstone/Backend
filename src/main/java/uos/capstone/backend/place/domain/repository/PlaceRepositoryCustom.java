package uos.capstone.backend.place.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import uos.capstone.backend.place.dto.response.PlaceResponse;
import uos.capstone.backend.place.dto.response.PlaceSimpleResponse;
import uos.capstone.backend.search.dto.PlaceSearchResponse;

public interface PlaceRepositoryCustom {

	Optional<PlaceResponse> findOnePlace(Long id);
	Slice<PlaceSimpleResponse> findAllOrderByPage(Pageable pageable);
	Slice<PlaceSimpleResponse> findAllByUser(Long userId, Pageable pageable);

	Slice<PlaceSearchResponse> findAllPlaceBySearch(String query, Pageable pageable);

}
