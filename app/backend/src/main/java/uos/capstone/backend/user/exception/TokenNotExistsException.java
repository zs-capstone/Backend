package uos.capstone.backend.user.exception;

import org.springframework.http.HttpStatus;

import uos.capstone.backend.common.exception.business.BusinessException;

public class TokenNotExistsException extends BusinessException {

	private static final String CLIENT_MESSAGE = "토큰이 존재하지 않습니다";

	public TokenNotExistsException() {super(CLIENT_MESSAGE, HttpStatus.FORBIDDEN);}

}
