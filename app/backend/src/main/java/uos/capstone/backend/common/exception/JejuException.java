package uos.capstone.backend.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import uos.capstone.backend.common.support.DebugMessage;

@Getter
public abstract class JejuException extends RuntimeException{
    private final String clientMessage;
    private final HttpStatus httpStatus;

    protected JejuException(final String clientMessage, final DebugMessage debugMessage, final HttpStatus httpStatus) {
        super(clientMessage + debugMessage.build());
        this.clientMessage = clientMessage;
        this.httpStatus = httpStatus;
    }
}
