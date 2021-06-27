package com.duynt.projectsecurity.exception;

import org.springframework.security.core.AuthenticationException;

public class PasswordNotInvalidException extends AuthenticationException {
    public PasswordNotInvalidException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public PasswordNotInvalidException(String msg) {
        super(msg);
    }
}
