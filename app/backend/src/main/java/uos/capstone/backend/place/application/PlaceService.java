package uos.capstone.backend.place.application;

import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import uos.capstone.backend.place.domain.mapper.PlaceResponseMapper;
import uos.capstone.backend.place.domain.repository.PlaceRepository;
import uos.capstone.backend.place.dto.PlaceResponse;
import uos.capstone.backend.place.exception.PlaceNotExistException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {

	private final PlaceRepository placeRepository;

	public PlaceResponse findById(Long id) {
		PlaceResponse response = PlaceResponseMapper.INSTANCE.toDto(
			placeRepository.findById(id)
			.orElseThrow(() -> new PlaceNotExistException()));

		return response;
	}

	// public Slice<PlaceResponse> findAllByLike(Long id) {
	// 	Slice<PlaceResponse> response = placeRepository.findById(id)
	// 		.orElseThrow(() -> new PlaceNotExistException())
	// 		.map(Plac)
	//
	// 	return response;
	// }
}
