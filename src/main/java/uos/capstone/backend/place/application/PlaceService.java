package uos.capstone.backend.place.application;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import uos.capstone.backend.place.domain.Place;
import uos.capstone.backend.place.domain.PlaceLike;
import uos.capstone.backend.place.domain.PlaceReview;
import uos.capstone.backend.place.domain.mapper.PlaceReviewCreateMapper;
import uos.capstone.backend.place.domain.mapper.PlaceReviewMapper;
import uos.capstone.backend.place.domain.repository.PlaceLikeRepository;
import uos.capstone.backend.place.domain.repository.PlaceRepository;
import uos.capstone.backend.place.domain.repository.PlaceReviewRepository;
import uos.capstone.backend.place.dto.request.PlaceReviewCreateRequest;
import uos.capstone.backend.place.dto.response.PlaceResponse;
import uos.capstone.backend.place.dto.response.PlaceReviewResponse;
import uos.capstone.backend.place.dto.response.PlaceSimpleResponse;
import uos.capstone.backend.place.exception.PlaceNotExistException;
import uos.capstone.backend.place.exception.PlaceReviewNotFoundException;
import uos.capstone.backend.user.domain.User;
import uos.capstone.backend.user.domain.UserRepository;
import uos.capstone.backend.user.exception.UserNotAuthorException;
import uos.capstone.backend.user.exception.UserNotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {

	private final PlaceRepository placeRepository;

	private final PlaceLikeRepository placeLikeRepository;

	private final UserRepository userRepository;

	private final PlaceReviewRepository placeReviewRepository;

	public Slice<PlaceSimpleResponse> findAll(Pageable pageable) {
		Slice<PlaceSimpleResponse> response = placeRepository.findAllOrderByPage(pageable);

		return response;
	}

	public PlaceResponse findById(Long id) {
		PlaceResponse response = placeRepository.findOnePlace(id)
			.orElseThrow(() -> new PlaceNotExistException());

		return response;
	}

	public Slice<PlaceSimpleResponse> findAllByLike(Long userId, Pageable pageable) {
		Slice<PlaceSimpleResponse> response = placeRepository.findAllByUser(userId, pageable);

		return response;
	}

	public Slice<PlaceReviewResponse> findReviewsByPlaceId(Long placeId, Pageable pageable) {
		Slice<PlaceReviewResponse> response = placeReviewRepository
			.findByPlace(placeRepository.findById(placeId)
				.orElseThrow(() -> new PlaceNotExistException()), pageable)
			.map(PlaceReviewMapper.INSTANCE::toDto);

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
	public void updatePlaceLikeById(Long placeId, Long userId) {
		Place place = placeRepository.findById(placeId)
			.orElseThrow(() -> new PlaceNotExistException());

		User user = userRepository.findById(userId)
			.orElseThrow(() -> new UserNotFoundException());

		Optional<PlaceLike> placeLike = placeLikeRepository.findByUserAndPlace(user,place);

		if (placeLike.isPresent()) {
			placeLikeRepository.delete(placeLike.get());
		} else {
			PlaceLike placeLike1 = new PlaceLike(user, place);
			placeLikeRepository.save(placeLike1);
		}

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

	@Transactional
	public Long savePlaceReview(Long placeId, Long userId, PlaceReviewCreateRequest placeReviewCreateRequest) {

		Long response = placeReviewRepository.save(
			PlaceReviewCreateMapper.INSTANCE.toEntity(
				userRepository.findById(userId)
					.orElseThrow(() -> new UserNotFoundException()),
				placeRepository.findById(placeId)
					.orElseThrow(() -> new PlaceNotExistException()),
				placeReviewCreateRequest)).getId();

		return response;
	}

	@Transactional
	public void updatePlaceReviewById(Long reviewId, Long userId, PlaceReviewCreateRequest placeReviewCreateRequest) {
		validateReviewAuthor(reviewId,userId);
		placeReviewRepository.findById(reviewId)
			.orElseThrow(() -> new PlaceReviewNotFoundException())
			.updateReview(placeReviewCreateRequest);
	}

	@Transactional
	public void deleteByReviewId(Long reviewId, Long userId) {
		validateReviewAuthor(reviewId,userId);
		placeReviewRepository.deleteById(reviewId);
	}

	private void validateReviewAuthor(Long reviewId, Long userId) {
		if (!(placeReviewRepository.findById(reviewId)
			.orElseThrow(() -> new PlaceReviewNotFoundException())
			.getUser()
			.equals(userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException())))) {
			throw new UserNotAuthorException();
		}
	}

}
