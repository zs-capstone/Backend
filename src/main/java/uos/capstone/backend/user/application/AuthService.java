package uos.capstone.backend.user.application;

import java.util.Optional;

import io.jsonwebtoken.io.DecodingException;
import lombok.RequiredArgsConstructor;
import uos.capstone.backend.common.config.jwt.JwtTokenUtil;
import uos.capstone.backend.common.config.jwt.enums.JwtExpirationEnums;
import uos.capstone.backend.common.config.security.CustomUserDetailsService;
import uos.capstone.backend.common.domain.refresh.RefreshToken;
import uos.capstone.backend.common.domain.refresh.RefreshTokenRedisRepository;
import uos.capstone.backend.user.domain.Token;
import uos.capstone.backend.user.domain.User;
import uos.capstone.backend.user.domain.UserRepository;
import uos.capstone.backend.user.domain.mapper.UserCreateMapper;
import uos.capstone.backend.user.domain.mapper.UserMapper;
import uos.capstone.backend.user.domain.mapper.UserTokenMapper;
import uos.capstone.backend.user.dto.request.UserCreateRequest;
import uos.capstone.backend.user.dto.request.UserEmailRequest;
import uos.capstone.backend.user.dto.request.UserLoginRequest;
import uos.capstone.backend.user.dto.request.UserNicknameRequest;
import uos.capstone.backend.user.dto.response.UserTokenResponse;
import uos.capstone.backend.user.exception.EmailExistsException;
import uos.capstone.backend.user.exception.EmailNotExistsException;
import uos.capstone.backend.user.exception.InvalidPasswordException;
import uos.capstone.backend.user.exception.NicknameExistsException;
import uos.capstone.backend.user.exception.RefreshTokenNotFoundException;
import uos.capstone.backend.user.exception.TokenErrorException;
import uos.capstone.backend.user.exception.TokenNotExistsException;
import uos.capstone.backend.user.exception.UserNotFoundException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

	private final JwtTokenUtil jwtTokenUtil;
	private final RefreshTokenRedisRepository refreshTokenRedisRepository;
	private final UserRepository userRepository;
	private final CustomUserDetailsService customUserDetailsService;

	private final PasswordEncoder passwordEncoder;

	@Transactional
	public Long save(final UserCreateRequest userCreateRequest) {
		checkEmail(userCreateRequest.getEmail());
		checkNickname(userCreateRequest.getNickname());
		String password = passwordEncoder.encode(userCreateRequest.getPassword());
		User user = UserCreateMapper.INSTANCE.toEntity(userCreateRequest,password);

		return userRepository.save(user).getId();
	}

	public void findEmailExists(final UserEmailRequest userEmailRequest) {
		checkEmail(userEmailRequest.getEmail());
	}

	public void findNicknameExists(final UserNicknameRequest userNicknameRequest) {
		checkNickname(userNicknameRequest.getNickname());
	}

	private void checkEmail(final String email) {
		if (userRepository.existsByEmail(email)){
			throw new EmailExistsException();
		}
	}

	private void checkNickname(final String nickname) {
		if (userRepository.existsByNickname(nickname)) {
			throw new NicknameExistsException();
		}
	}

	@Transactional
	public UserTokenResponse reissue(String refreshToken) {
		checkRefresh(refreshToken);
		String id = jwtTokenUtil.getUsername(refreshToken);
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(id);
		validateRefreshToken(refreshToken,userDetails);

		RefreshToken refreshToken1 = refreshTokenRedisRepository.findById(id)
			.orElseThrow(() -> new RefreshTokenNotFoundException());

		Token token = Token.builder()
			.accessToken(jwtTokenUtil.generateAccessToken(id))
			.refreshToken(refreshToken1.getRefreshToken())
			.build();

		User user = userRepository.findById(Long.parseLong(id))
			.orElseThrow(() -> new UserNotFoundException());


		return UserTokenMapper.INSTANCE.toDto(token, UserMapper.INSTANCE.toDto(user));
	}

	public UserTokenResponse login(final UserLoginRequest userLoginRequest) {
		User user = userRepository.findByemail(userLoginRequest.getEmail())
			.orElseThrow(() -> new EmailNotExistsException());
		checkPassword(userLoginRequest.getPassword(), user.getPassword());

		UserTokenResponse response = UserTokenResponse.builder()
			.userResponse(UserMapper.INSTANCE.toDto(user))
			.accessToken(jwtTokenUtil.generateAccessToken(user.getId().toString()))
			.refreshToken(saveRefreshToken(user.getId().toString()).getRefreshToken())
			.build();

		return response;
	}

	private RefreshToken saveRefreshToken(String username) {

		return refreshTokenRedisRepository.save(RefreshToken.createRefreshToken(username,
			jwtTokenUtil.generateRefreshToken(username), JwtExpirationEnums.REFRESH_TOKEN_EXPIRATION_TIME.getValue()));
	}

	private void checkPassword(String rawPassword, String findMemberPassword) {
		if (!passwordEncoder.matches(rawPassword, findMemberPassword)) {
			throw new InvalidPasswordException();
		}
	}

	private void checkRefresh(String refreshToken) {
		if (!(StringUtils.hasText(refreshToken))) {
			throw new TokenNotExistsException();
		}
	}

	private void validateRefreshToken(String refreshToken,UserDetails userDetails) {
		if (!jwtTokenUtil.validateToken(refreshToken, userDetails)) {
			throw new TokenErrorException();
		}
	}

}
