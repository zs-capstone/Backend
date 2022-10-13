package uos.capstone.backend.member.exception;

import org.springframework.http.HttpStatus;
import uos.capstone.backend.common.exception.JejuException;
import uos.capstone.backend.common.support.DebugMessage;

public class EmailTransferFailedException extends JejuException {
    private static final String CLIENT_MESSAGE =  "이메일이 전송 실패";

    protected EmailTransferFailedException(DebugMessage debugMessage) {
        super(CLIENT_MESSAGE, debugMessage, HttpStatus.BAD_REQUEST);
    }
}
