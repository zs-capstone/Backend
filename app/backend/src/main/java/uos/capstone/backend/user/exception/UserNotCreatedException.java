package uos.capstone.backend.user.exception;


import org.springframework.http.HttpStatus;

import uos.capstone.backend.common.exception.business.BusinessException;

public class UserNotCreatedException extends BusinessException {
	private static final String CLIENT_MESSAGE = "사용자 생성 실패";

	public UserNotCreatedException() {
		super(CLIENT_MESSAGE, HttpStatus.BAD_REQUEST);
	}
}
