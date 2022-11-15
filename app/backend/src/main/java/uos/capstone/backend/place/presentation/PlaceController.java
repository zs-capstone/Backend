package uos.capstone.backend.place.presentation;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<ResponseDto> findAllByLike(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
		ResponseDto responseDto = ResponseDto.of(
			placeService.findAllByLike(customUserDetails.getId()));

		return ResponseEntity.ok(responseDto);
	}

}
