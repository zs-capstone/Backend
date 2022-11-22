package uos.capstone.backend.place.exception;

import org.springframework.http.HttpStatus;

import uos.capstone.backend.common.exception.business.BusinessException;

public class PlaceReviewNotFoundException extends BusinessException {

	private static final String CLIENT_MESSAGE = "리뷰가 존재하지 않습니다.";

	public PlaceReviewNotFoundException() {
		super(CLIENT_MESSAGE, HttpStatus.BAD_REQUEST);
	}

}
