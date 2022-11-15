package uos.capstone.backend.user.application;

import java.io.FileNotFoundException;
import java.util.List;

import com.amazonaws.services.s3.AmazonS3Client;

import io.jsonwebtoken.io.DecodingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uos.capstone.backend.common.config.jwt.JwtTokenUtil;
import uos.capstone.backend.common.domain.access.LogoutAccessToken;
import uos.capstone.backend.common.domain.access.LogoutAccessTokenRedisRepository;
import uos.capstone.backend.common.domain.refresh.RefreshTokenRedisRepository;
import uos.capstone.backend.common.exception.business.FileUploadFailedException;
import uos.capstone.backend.common.utils.S3Utils;
import uos.capstone.backend.user.domain.User;
import uos.capstone.backend.user.domain.UserRepository;
import uos.capstone.backend.user.domain.mapper.UserInfoMapper;
import uos.capstone.backend.user.dto.request.UserUpdateRequest;
import uos.capstone.backend.user.dto.response.UserInfoResponse;
import uos.capstone.backend.user.exception.NicknameExistsException;
import uos.capstone.backend.user.exception.TokenErrorException;
import uos.capstone.backend.user.exception.UserNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final RefreshTokenRedisRepository refreshTokenRedisRepository;
	private final LogoutAccessTokenRedisRepository logoutAccessTokenRedisRepository;
	private final JwtTokenUtil jwtTokenUtil;
	private final AmazonS3Client amazonS3Client;
	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;



	public Long logout(String accessToken, String refreshToken) {
		checkToken(accessToken, refreshToken);
		String token = accessToken.substring(7);
		String username = jwtTokenUtil.getUsername(token);
		Long ms = jwtTokenUtil.getRemainMilliSeconds(token);

		logoutAccessTokenRedisRepository.save(
			LogoutAccessToken.of(token, username, ms)
		);
		refreshTokenRedisRepository.deleteById(username);

		return userRepository.findBynickname(username)
			.orElseThrow(() -> new UserNotFoundException()).getId();
	}

	private void checkToken(String accessToken, String refreshToken) {
		if (!(StringUtils.hasText(accessToken) && StringUtils.hasText(refreshToken) && accessToken.startsWith("Bearer "))) {
			throw new TokenErrorException();
		}
	}

	public UserInfoResponse findById(Long id) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new UserNotFoundException());

		return UserInfoMapper.INSTANCE.toDto(user);
	}

	@Transactional
	public void updateById(Long id, UserUpdateRequest userUpdateRequest) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new UserNotFoundException());

		if (userUpdateRequest.getNickname() != "" &&
			userRepository.existsByNickname(userUpdateRequest.getNickname())) {
			throw new NicknameExistsException();
		}

		user.updateUser(userUpdateRequest);
	}

	@Transactional
	public void updateImg(Long id,MultipartFile multipartFile) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new UserNotFoundException());

		if (multipartFile == null) {
			deleteFile(user);
		}
		else{
			if (multipartFile.isEmpty()) {
				throw new FileUploadFailedException();
			}
			uploadFile(multipartFile,user);
		}

	}

	@Transactional
	public void deleteFile(User user) {
		user.deleteImg();
	}

	@Transactional
	public void uploadFile(MultipartFile multipartFile, User user) {
		String fileName = S3Utils.uploadFileS3(amazonS3Client, bucketName, multipartFile);
		user.updateImg(amazonS3Client.getUrl(bucketName, fileName).toString());

	}

	@Transactional
	public void deleteById(String accessToken, String refreshToken, Long id) {
		checkToken(accessToken, refreshToken);
		String token = accessToken.substring(7);
		String username = jwtTokenUtil.getUsername(token);
		Long ms = jwtTokenUtil.getRemainMilliSeconds(token);

		logoutAccessTokenRedisRepository.save(
			LogoutAccessToken.of(token, username, ms)
		);
		refreshTokenRedisRepository.deleteById(username);
		userRepository.deleteById(id);

	}

}
