package uos.capstone.backend.member.exception;

import org.springframework.http.HttpStatus;
import uos.capstone.backend.common.exception.JejuException;
import uos.capstone.backend.common.support.DebugMessage;

public class MemberExistsException extends JejuException {

    private static final String CLIENT_MESSAGE = "회원이 이미 존재합니다.";

    protected MemberExistsException(DebugMessage debugMessage) {
        super(CLIENT_MESSAGE, debugMessage, HttpStatus.BAD_REQUEST);
    }
}
