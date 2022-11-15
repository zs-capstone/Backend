package uos.capstone.backend.place.domain.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import uos.capstone.backend.place.dto.PlaceResponse;

public interface PlaceRepositoryCustom {

	Slice<PlaceResponse> findAllByUser(Long userId, Pageable pageable);

}
