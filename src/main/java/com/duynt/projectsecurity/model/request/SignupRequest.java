package com.duynt.projectsecurity.model.request;

import com.duynt.projectsecurity.common.ResponseConstant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupRequest {

    @Email
    @NotBlank(message = ResponseConstant.MSG_EMAIL_NOTBLANK)
    @Size(max = 50)
    private String email;

    @NotBlank(message = ResponseConstant.MSG_USER_NOTBLANK)
    @Size(max = 30)
    private String username;

    @NotBlank(message = ResponseConstant.MSG_PASSWORD_NOTBLANK)
    @Size(max = 50)
    private String password;

    public SignupRequest(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
