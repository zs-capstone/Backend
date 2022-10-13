package uos.capstone.backend.member.exception;

import org.springframework.http.HttpStatus;
import uos.capstone.backend.common.exception.JejuException;
import uos.capstone.backend.common.support.DebugMessage;

public class NicknameExistsException extends JejuException {

    private static final String CLIENT_MESSAGE =  "닉네임이 이미 존재합니다.";

    public NicknameExistsException(DebugMessage debugMessage) {
        super(CLIENT_MESSAGE, debugMessage, HttpStatus.BAD_REQUEST);
    }
}
