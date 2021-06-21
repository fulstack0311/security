package com.duynt.projectsecurity.controllers;

import com.duynt.projectsecurity.config.security.JWTUserDetailService;
import com.duynt.projectsecurity.config.security.JwtTokenUtil;
import com.duynt.projectsecurity.payload.request.JwtRequest;
import com.duynt.projectsecurity.payload.response.JwtResponse;
import com.duynt.projectsecurity.payload.response.ResponseMsg;
import com.duynt.projectsecurity.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JWTUserDetailService jwtUserDetailService;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthencationToken(@Valid @RequestBody JwtRequest user) {
        UserDetailsImpl userDetails = null;
        try {
            userDetails = jwtUserDetailService.loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMsg("404", "User not found!"));
        }
        String pass = encoder.encode(user.getPassword());
        if (!pass.equals(userDetails.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMsg("401", "Password is invalid!"));
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
