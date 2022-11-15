// package uos.capstone.backend.survey.presentation;
//
// import java.net.URI;
//
// import javax.websocket.server.PathParam;
//
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.annotation.AuthenticationPrincipal;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
//
// import lombok.RequiredArgsConstructor;
// import uos.capstone.backend.common.config.security.CustomUserDetails;
// import uos.capstone.backend.common.dto.ResponseDto;
// import uos.capstone.backend.survey.application.SurveyService;
//
// @RestController
// @RequestMapping("/api/survey")
// @RequiredArgsConstructor
// public class SurveyController {
//
// 	private final SurveyService surveyService;
//
// 	@GetMapping("/{progress}")
// 	public ResponseEntity<ResponseDto> findByProgress(
// 		@PathParam("progress") Integer progress) {
// 		ResponseDto responseDto = ResponseDto.of(
// 			surveyService.findByProgress(progress));
//
// 		return ResponseEntity.ok(responseDto);
// 	}
//
// 	@GetMapping("/progress")
// 	public ResponseEntity<ResponseDto> findByUser(
// 		@AuthenticationPrincipal CustomUserDetails customUserDetails) {
// 		ResponseDto responseDto = ResponseDto.of(
// 			surveyService.findByUser(customUserDetails.getId()));
//
// 		return ResponseEntity.ok(responseDto);
// 	}
//
// 	@PostMapping("/result")
// 	public ResponseEntity<ResponseDto> save(
// 		@AuthenticationPrincipal CustomUserDetails customUserDetails,
// 		@RequestBody) {
//
// 		return ResponseEntity.created(URI.create("/api/survey/result/"+customUserDetails.getUsername())).build();
// 	}
//
// }
