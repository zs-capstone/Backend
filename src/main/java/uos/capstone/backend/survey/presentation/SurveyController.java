package uos.capstone.backend.survey.presentation;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import uos.capstone.backend.common.config.security.CustomUserDetails;
import uos.capstone.backend.common.dto.ResponseDto;
import uos.capstone.backend.survey.application.SurveyService;
import uos.capstone.backend.survey.dto.request.SurveyCreateRequest;

@RestController
@RequestMapping("/api/surveys")
@RequiredArgsConstructor
public class SurveyController {

	private final SurveyService surveyService;

	@GetMapping
	public ResponseEntity<ResponseDto> giveRandomSurvey(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
		ResponseDto<?> response = ResponseDto.of(
			surveyService.giveRandomSurvey(customUserDetails.getId()));

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ResponseDto> save(@AuthenticationPrincipal CustomUserDetails customUserDetails,
		@RequestBody @Valid SurveyCreateRequest surveyCreateRequest) {
		surveyService.save(customUserDetails.getId(),surveyCreateRequest);

		return ResponseEntity.created(URI.create("/api/surveys/")).build();
	}

	@DeleteMapping
	public ResponseEntity<ResponseDto> deleteById(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
		surveyService.deleteById(customUserDetails.getId());

		return ResponseEntity.noContent().build();
	}
}
