package uos.capstone.backend.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = EmailExistsException.class)
    public String handleEmailExistsException(EmailExistsException e) {
        return "이미 존재하는 이메일";
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NicknameExistsException.class)
    public String handleNicknameExistsException(NicknameExistsException e) {
        return "이미 존재하는 닉네임";
    }

}
