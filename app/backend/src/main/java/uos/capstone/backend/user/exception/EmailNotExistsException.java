package uos.capstone.backend.user.exception;

import org.springframework.http.HttpStatus;

import uos.capstone.backend.common.exception.business.BusinessException;

public class EmailNotExistsException extends BusinessException {
	private static final String CLIENT_MESSAGE = "이메일이 존재하지 않습니다";

	public EmailNotExistsException() {
		super(CLIENT_MESSAGE, HttpStatus.BAD_REQUEST);
	}
}
