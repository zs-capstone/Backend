package uos.capstone.backend.note.presentation;

import java.net.URI;

import javax.validation.Valid;

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
import uos.capstone.backend.common.dto.response.ListEvalResponse;
import uos.capstone.backend.common.utils.FlaskUtils;
import uos.capstone.backend.note.application.NoteService;
import uos.capstone.backend.note.dto.request.NoteCreateRequest;
import uos.capstone.backend.note.dto.request.NoteUpdateRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notes")
public class NoteController {

	private final NoteService noteService;

	@PostMapping
	public ResponseEntity<ResponseDto> save(@AuthenticationPrincipal CustomUserDetails customUserDetails,
		@RequestBody @Valid NoteCreateRequest noteCreateRequest) {
		noteService.save(customUserDetails.getId(),noteCreateRequest);

		return ResponseEntity.created(URI.create("/api/notes/")).build();
	}

	@GetMapping("/{noteId}")
	public ResponseEntity<ResponseDto> findById(@AuthenticationPrincipal CustomUserDetails customUserDetails,
		@PathVariable("noteId") Long noteId) {
		ResponseDto responseDto = ResponseDto.of(
			noteService.findById(customUserDetails.getId(),noteId));

		return ResponseEntity.ok(responseDto);
	}

	@PutMapping("/{noteId}")
	public ResponseEntity<ResponseDto> updateById(@AuthenticationPrincipal CustomUserDetails customUserDetails,
		@PathVariable("noteId") Long noteId,
		@RequestBody @Valid NoteUpdateRequest noteUpdateRequest) {
		noteService.updateById(customUserDetails.getId(), noteId, noteUpdateRequest);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{noteId}")
	public ResponseEntity<ResponseDto> deleteById(@AuthenticationPrincipal CustomUserDetails customUserDetails,
		@PathVariable("noteId") Long noteId) {
		noteService.deleteById(customUserDetails.getId(),noteId);

		return ResponseEntity.noContent().build();
	}

}
