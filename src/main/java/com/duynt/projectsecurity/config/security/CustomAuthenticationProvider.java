package com.duynt.projectsecurity.config.security;

import com.duynt.projectsecurity.common.ResponseConstant;
import com.duynt.projectsecurity.exception.PasswordNotInvalidException;
import com.duynt.projectsecurity.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JWTUserDetailService userDetailService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetailsImpl userDetails = userDetailService.loadUserByUsername(username);
        boolean ckPassword = encoder.matches(password, userDetails.getPassword());
        if (!ckPassword) {
            throw new PasswordNotInvalidException(ResponseConstant.MSG_PASSWORD_NOT_INVALID);
        }
        return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
