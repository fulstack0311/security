package com.duynt.projectsecurity.exception;

import com.duynt.projectsecurity.common.ResponseConstant;
import com.duynt.projectsecurity.model.response.ResponseMsg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
public class ControllerExceptionHandle {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> userNotFoundException(UsernameNotFoundException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put(ResponseConstant.KEY_ERROR, ex.getMessage());
        ResponseMsg responseMsg = new ResponseMsg(HttpStatus.NOT_FOUND.value(), new Date().toString(), error);
        return new ResponseEntity(responseMsg, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordNotInvalidException.class)
    public ResponseEntity<?> passwordNotInvalidException(PasswordNotInvalidException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put(ResponseConstant.KEY_ERROR, ex.getMessage());
        ResponseMsg responseMsg = new ResponseMsg(HttpStatus.UNAUTHORIZED.value(), new Date().toString(), error);
        return new ResponseEntity(responseMsg, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> error = new HashMap<>();
        Map<String, Object> body = new HashMap<>();
        List<ObjectError> listError = exception.getBindingResult().getAllErrors();
        for (ObjectError er : listError) {
            String field = ((FieldError) er).getField();
            String message = er.getDefaultMessage();
            error.put(field, message);
        }
        body.put(ResponseConstant.KEY_ERROR, error);
        ResponseMsg responseMsg = new ResponseMsg(HttpStatus.BAD_REQUEST.value(), new Date().toString(), body);
        return new ResponseEntity(responseMsg, HttpStatus.BAD_REQUEST);
    }
}
