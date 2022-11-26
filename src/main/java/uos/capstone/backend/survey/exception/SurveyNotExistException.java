package uos.capstone.backend.survey.exception;

import org.springframework.http.HttpStatus;

import uos.capstone.backend.common.exception.business.BusinessException;

public class SurveyNotExistException extends BusinessException {
	private static final String CLIENT_MESSAGE = "설문이 존재하지 않습니다.";

	public SurveyNotExistException() {
		super(CLIENT_MESSAGE, HttpStatus.BAD_REQUEST);
	}
}
