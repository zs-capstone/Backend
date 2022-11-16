package uos.capstone.backend.place.application;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import uos.capstone.backend.place.domain.PlaceLike;
import uos.capstone.backend.place.domain.mapper.PlaceResponseMapper;
import uos.capstone.backend.place.domain.repository.PlaceLikeRepository;
import uos.capstone.backend.place.domain.repository.PlaceRepository;
import uos.capstone.backend.place.dto.PlaceResponse;
import uos.capstone.backend.place.exception.PlaceNotExistException;
import uos.capstone.backend.user.domain.UserRepository;
import uos.capstone.backend.user.exception.UserNotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {

	private final PlaceRepository placeRepository;

	private final PlaceLikeRepository placeLikeRepository;

	private final UserRepository userRepository;

	public PlaceResponse findById(Long id) {
		PlaceResponse response = PlaceResponseMapper.INSTANCE.toDto(
			placeRepository.findById(id)
			.orElseThrow(() -> new PlaceNotExistException()));

		return response;
	}

	public Slice<PlaceResponse> findAllByLike(Long userId, Pageable pageable) {
		Slice<PlaceResponse> response = placeRepository.findAllByUser(userId, pageable);

		return response;
	}

	@Transactional
	public Long savePlaceLike(Long placeId, Long userId) {

		return placeLikeRepository.save(
			PlaceLike.builder()
				.place(
					placeRepository.findById(placeId)
						.orElseThrow(() -> new PlaceNotExistException()))
				.user(
					userRepository.findById(userId)
						.orElseThrow(() -> new UserNotFoundException()))
				.build()).getId();
	}

	@Transactional
	public void deleteById(Long placeId, Long userId) {
		placeLikeRepository.deleteByUserAndPlace(
			userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException()),
			placeRepository.findById(placeId)
				.orElseThrow(() -> new PlaceNotExistException())
		);
	}
}
