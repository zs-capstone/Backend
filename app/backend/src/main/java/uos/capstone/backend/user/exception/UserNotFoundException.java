package uos.capstone.backend.user.exception;

import org.springframework.http.HttpStatus;

import uos.capstone.backend.common.exception.business.BusinessException;

public class UserNotFoundException extends BusinessException {
	private static final String CLIENT_MESSAGE = "사용자가 존재하지 않습니다.";
	public UserNotFoundException() {
		super(CLIENT_MESSAGE, HttpStatus.BAD_REQUEST);
	}
}

