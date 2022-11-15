package uos.capstone.backend.user.exception;

import org.springframework.http.HttpStatus;
import uos.capstone.backend.common.exception.business.BusinessException;

public class EmailExistsException extends BusinessException {

	private static final String CLIENT_MESSAGE = "이메일이 존재합니다.";
	public EmailExistsException() {
		super(CLIENT_MESSAGE, HttpStatus.BAD_REQUEST);
	}
}
