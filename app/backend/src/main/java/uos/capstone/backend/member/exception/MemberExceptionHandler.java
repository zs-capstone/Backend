package uos.capstone.backend.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uos.capstone.backend.member.controller.MemberController;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class MemberExceptionHandler {

    @ExceptionHandler(value = EmailExistsException.class)
    public ResponseEntity<String> handleEmailExistsException(EmailExistsException e) {
        return ResponseEntity.badRequest().body("이미 존재하는 이메일");
    }

    @ExceptionHandler(value = NicknameExistsException.class)
    public ResponseEntity<String> handleNicknameExistsException(NicknameExistsException e) {
        return ResponseEntity.badRequest().body("이미 존재하는 닉네임");
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.badRequest().body("없는 회원");
    }

}
