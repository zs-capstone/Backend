package uos.capstone.backend.member.exception;

import org.springframework.http.HttpStatus;
import uos.capstone.backend.common.exception.JejuException;
import uos.capstone.backend.common.support.DebugMessage;

public class PasswordValidateException extends JejuException {
    private static final String CLIENT_MESSAGE =  "비밀번호가 일치하지 않습니다";

    protected PasswordValidateException(DebugMessage debugMessage) {
        super(CLIENT_MESSAGE, debugMessage, HttpStatus.BAD_REQUEST);
    }
}
