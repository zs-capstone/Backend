package uos.capstone.backend.place.presentation;

import java.net.URI;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import uos.capstone.backend.common.config.security.CustomUserDetails;
import uos.capstone.backend.common.dto.ResponseDto;
import uos.capstone.backend.place.application.PlaceService;

@RestController
@RequestMapping("/api/place")
@RequiredArgsConstructor
public class PlaceController {

	private final PlaceService placeService;

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

	@PostMapping("/{placeId}/like")
	public ResponseEntity<ResponseDto> savePlaceLike(@PathVariable("placeId") Long id,
		@AuthenticationPrincipal CustomUserDetails customUserDetails) {
		final Long PLId = placeService.savePlaceLike(id,customUserDetails.getId());

		return ResponseEntity.created(URI.create("/api/place/"+id+"/like/"+PLId)).build();
	}

	@DeleteMapping("/{placeId}/like")
	public ResponseEntity<ResponseDto> deletePlaceLike(@PathVariable("placeId") Long id,
		@AuthenticationPrincipal CustomUserDetails customUserDetails) {
		placeService.deleteById(id,customUserDetails.getId());

		return ResponseEntity.noContent().build();
	}

}
