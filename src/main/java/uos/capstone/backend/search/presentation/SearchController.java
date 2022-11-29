package uos.capstone.backend.search.presentation;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import uos.capstone.backend.common.dto.ResponseDto;
import uos.capstone.backend.search.application.SearchService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class SearchController {

	private final SearchService searchService;

	@GetMapping("/place")
	public ResponseEntity<ResponseDto> findAllPlaceBySearch(
		@RequestParam("content") String content,
		@PageableDefault(size = 10, sort = "title", direction = Sort.Direction.ASC) Pageable pageable
	) {
		ResponseDto responseDto = ResponseDto.of(
			searchService.findAllPlaceBySearch(content, pageable));

		return ResponseEntity.ok(responseDto);
	}

	@GetMapping("/user")
	public ResponseEntity<ResponseDto> findAllUserBySearch(
		@RequestParam("content") String content,
		@PageableDefault(size = 10, sort = "nickname", direction = Sort.Direction.ASC) Pageable pageable
	) {
		ResponseDto responseDto = ResponseDto.of(
			searchService.findAllUserBySearch(content, pageable));

		return ResponseEntity.ok(responseDto);
	}

	@GetMapping("/note")
	public ResponseEntity<ResponseDto> findAllNoteBySearch(
		@RequestParam("content") String content,
		@PageableDefault(size = 10, sort = "title", direction = Sort.Direction.ASC) Pageable pageable
	) {
		ResponseDto responseDto = ResponseDto.of(
			searchService.findAllNoteBySearch(content, pageable));

		return ResponseEntity.ok(responseDto);
	}

}
