package uos.capstone.backend.place.exception;

import org.springframework.http.HttpStatus;

import uos.capstone.backend.common.exception.business.BusinessException;

public class PlaceNotExistException extends BusinessException {

	private static final String CLIENT_MESSAGE = "장소가 존재하지 않습니다.";

	public PlaceNotExistException() {
		super(CLIENT_MESSAGE, HttpStatus.BAD_REQUEST);
	}

}
