package uos.capstone.backend.place.presentation;

import java.net.URI;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import uos.capstone.backend.common.config.security.CustomUserDetails;
import uos.capstone.backend.common.dto.ResponseDto;
import uos.capstone.backend.place.application.PlaceService;
import uos.capstone.backend.place.dto.request.PlaceReviewCreateRequest;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
public class PlaceController {

	private final PlaceService placeService;

	@GetMapping
	public ResponseEntity<ResponseDto> findAll(
		@PageableDefault(size=10,sort="title",direction = Sort.Direction.ASC) Pageable pageable
	) {
		ResponseDto responseDto = ResponseDto.of(
			placeService.findAll(pageable));

		return ResponseEntity.ok(responseDto);
	}

	@GetMapping("/{placeId}")
	public ResponseEntity<ResponseDto> findById(@PathVariable("placeId") Long id) {
		ResponseDto responseDto = ResponseDto.of(
			placeService.findById(id));

		return ResponseEntity.ok(responseDto);
	}

	@GetMapping("/like/list")
	public ResponseEntity<ResponseDto> findAllByLike(@AuthenticationPrincipal CustomUserDetails customUserDetails,
		@PageableDefault(size = 10, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
		ResponseDto responseDto = ResponseDto.of(
			placeService.findAllByLike(customUserDetails.getId(),pageable));

		return ResponseEntity.ok(responseDto);
	}

	@PutMapping("/{placeId}/like")
	public ResponseEntity<ResponseDto> deletePlaceLike(@PathVariable("placeId") Long id,
		@AuthenticationPrincipal CustomUserDetails customUserDetails) {
		placeService.updatePlaceLikeById(id,customUserDetails.getId());

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{placeId}/reviews")
	public ResponseEntity<ResponseDto> findReviewsByPlaceId(@PathVariable("placeId") Long id,
		@PageableDefault(size=10,sort="rate",direction = Sort.Direction.DESC) Pageable pageable) {
		ResponseDto<?> response = ResponseDto.of(
			placeService.findReviewsByPlaceId(id,pageable));

		return ResponseEntity.ok(response);
	}

	@PostMapping("/{placeId}/review")
	public ResponseEntity<ResponseDto> savePlaceReview(@PathVariable("placeId") Long id,
		@Valid @RequestBody PlaceReviewCreateRequest placeReviewCreateRequest,
		@AuthenticationPrincipal CustomUserDetails customUserDetails) {
		Long reviewId = placeService.savePlaceReview(id,customUserDetails.getId(),placeReviewCreateRequest);

		return ResponseEntity.created(URI.create("/api/places/"+id+"/review/"+reviewId)).build();
	}

	@PutMapping("/{placeId}/review/{reviewId}")
	public ResponseEntity<ResponseDto> updatePlaceReviewById(@PathVariable("reviewId") Long id,
		@Valid @RequestBody PlaceReviewCreateRequest placeReviewCreateRequest,
		@AuthenticationPrincipal CustomUserDetails customUserDetails) {
		placeService.updatePlaceReviewById(id,customUserDetails.getId(),placeReviewCreateRequest);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{placeId}/review/{reviewId}")
	public ResponseEntity<ResponseDto> deleteByReviewId(@PathVariable("reviewId") Long id,
		@AuthenticationPrincipal CustomUserDetails customUserDetails) {
		placeService.deleteByReviewId(id,customUserDetails.getId());

		return ResponseEntity.noContent().build();
	}

}
