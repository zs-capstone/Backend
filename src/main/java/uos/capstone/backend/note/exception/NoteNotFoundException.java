package uos.capstone.backend.note.exception;

import org.springframework.http.HttpStatus;

import uos.capstone.backend.common.exception.business.BusinessException;

public class NoteNotFoundException extends BusinessException {

	private static final String CLIENT_MESSAGE = "노트가 존재하지 않습니다";
	public NoteNotFoundException() {
		super(CLIENT_MESSAGE, HttpStatus.BAD_REQUEST);
	}
}
