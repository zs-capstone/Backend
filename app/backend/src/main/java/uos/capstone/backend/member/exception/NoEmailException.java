package uos.capstone.backend.member.exception;

import org.springframework.http.HttpStatus;
import uos.capstone.backend.common.exception.JejuException;
import uos.capstone.backend.common.support.DebugMessage;

public class NoEmailException extends JejuException {

    private static final String CLIENT_MESSAGE =  "이메일이 존재하지 않습니다.";

    protected NoEmailException(DebugMessage debugMessage) {
        super(CLIENT_MESSAGE, debugMessage, HttpStatus.BAD_REQUEST);
    }
}
