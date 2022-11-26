package uos.capstone.backend.survey.exception;

import org.springframework.http.HttpStatus;

import uos.capstone.backend.common.domain.BaseEntity;
import uos.capstone.backend.common.exception.business.BusinessException;

public class NotEnoughSurveyQueryException extends BusinessException {

	private static final String CLIENT_MESSAGE = "설문 질문 개수가 부족합니다.";

	public NotEnoughSurveyQueryException() {
		super(CLIENT_MESSAGE, HttpStatus.BAD_REQUEST);
	}

}
