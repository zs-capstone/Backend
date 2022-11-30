package uos.capstone.backend.user.presentation;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.RequiredArgsConstructor;
import uos.capstone.backend.common.dto.ResponseDto;
import uos.capstone.backend.user.application.AuthService;
import uos.capstone.backend.user.dto.request.UserCreateRequest;
import uos.capstone.backend.user.dto.request.UserEmailRequest;
import uos.capstone.backend.user.dto.request.UserLoginRequest;
import uos.capstone.backend.user.dto.request.UserNicknameRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<Void> save(@RequestBody @Valid UserCreateRequest userCreateRequest) {
		Long id = authService.save(userCreateRequest);

		return ResponseEntity.created(URI.create("/api/auth/signup/" + id)).build();
	}


	@PostMapping("/login")
	public ResponseEntity<ResponseDto> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
		ResponseDto responseDto = ResponseDto.of(authService.login(userLoginRequest));

		return ResponseEntity.ok(responseDto);
	}

	@GetMapping("/reissue")
	public ResponseEntity<ResponseDto> reissue(@RequestHeader("RefreshToken") String refreshToken) {
		ResponseDto responseDto = ResponseDto.of(authService.reissue(refreshToken));
		return ResponseEntity.ok(responseDto);
	}

	@PostMapping("/email")
	public ResponseEntity<ResponseDto> findEmailExists(@RequestBody UserEmailRequest userEmailRequest) {
		authService.findEmailExists(userEmailRequest);
		ResponseDto responseDto = ResponseDto.of("이메일 사용 가능합니다.");

		return ResponseEntity.ok(responseDto);
	}

	@PostMapping("/nickname")
	public ResponseEntity<ResponseDto> findNicknameExists(@RequestBody UserNicknameRequest userNicknameRequest) {
		authService.findNicknameExists(userNicknameRequest);
		ResponseDto responseDto = ResponseDto.of("닉네임 사용 가능합니다.");

		return ResponseEntity.ok(responseDto);
	}

}
