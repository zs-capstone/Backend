package uos.capstone.backend.survey.exception;

import org.springframework.http.HttpStatus;

import uos.capstone.backend.common.exception.business.BusinessException;

public class SurveyExistsException extends BusinessException {

	private static final String CLIENT_MESSAGE = "이미 설문을 진행한 유저입니다.";

	public SurveyExistsException() {
		super(CLIENT_MESSAGE, HttpStatus.BAD_REQUEST);
	}
}
