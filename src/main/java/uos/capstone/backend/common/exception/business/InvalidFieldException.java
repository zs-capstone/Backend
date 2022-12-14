package uos.capstone.backend.common.exception.business;

import org.springframework.http.HttpStatus;

public class InvalidFieldException extends BusinessException {
	public InvalidFieldException(final String clientMessage) {
		super(clientMessage, HttpStatus.BAD_REQUEST);
	}
}
