package uos.capstone.backend.survey.exception;

import org.springframework.http.HttpStatus;

import uos.capstone.backend.common.exception.business.BusinessException;

public class UserNotDidSurveyException extends BusinessException {

	private static final String CLIENT_MESSAGE = "유저가 설문을 진행하지 않아 노트를 생성할 수 없습니다.";

	public UserNotDidSurveyException() {
		super(CLIENT_MESSAGE, HttpStatus.BAD_REQUEST);
	}
}
