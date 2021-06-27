package com.duynt.projectsecurity.model.request;

import com.duynt.projectsecurity.common.ResponseConstant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class JwtRequest {

    @NotBlank(message = ResponseConstant.MSG_USER_NOTBLANK)
    @Size(min = 5, max = 30, message = ResponseConstant.MSG_USERNAME_MIN_MAX_CHAR)
    private String username;

    @NotBlank(message = ResponseConstant.MSG_PASSWORD_NOTBLANK)
    @Size(min = 6, max = 50, message = ResponseConstant.MSG_PASSWORD_MIN_MAX_CHAR)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
