// package uos.capstone.backend.note.presentation;
//
// import javax.validation.Valid;
//
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.annotation.AuthenticationPrincipal;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.RestController;
//
// import lombok.RequiredArgsConstructor;
// import uos.capstone.backend.common.config.security.CustomUserDetails;
// import uos.capstone.backend.common.dto.ResponseDto;
// import uos.capstone.backend.note.application.NoteService;
// import uos.capstone.backend.note.domain.repository.RecommendRepository;
// import uos.capstone.backend.note.dto.request.NoteCreateRequest;
//
// @RestController
// @RequiredArgsConstructor
// @RequestMapping("/api/note")
// public class NoteController {
//
// 	private final NoteService noteService;
//
// 	@PostMapping
// 	public ResponseEntity<ResponseDto> save(@AuthenticationPrincipal CustomUserDetails customUserDetails,
// 		@RequestBody @Valid NoteCreateRequest noteCreateRequest) {
// 		ResponseDto responseDto = ResponseDto.of(
// 			noteService.save(customUserDetails.getId(),noteCreateRequest));
//
// 		return ResponseEntity.ok(responseDto);
// 	}
//
// 	// @GetMapping("/{noteId}")
// 	// public ResponseEntity<ResponseDto> findById(@AuthenticationPrincipal CustomUserDetails customUserDetails,
// 	// 	@RequestParam("noteId") Long noteId) {
// 	// 	ResponseDto responseDto = ResponseDto.of(
// 	// 		noteService.findById(customUserDetails.getId(),noteId));
// 	//
// 	// 	return ResponseEntity.ok(responseDto);
// 	// }
//
// 	@DeleteMapping("/{noteId}")
// 	public ResponseEntity<ResponseDto> deleteById(@AuthenticationPrincipal CustomUserDetails customUserDetails,
// 		@RequestParam("noteId") Long noteId) {
// 		noteService.deleteById(customUserDetails.getId(),noteId);
//
// 		return ResponseEntity.noContent().build();
// 	}
//
// }
