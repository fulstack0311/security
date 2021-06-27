package com.duynt.projectsecurity.controllers;

import com.duynt.projectsecurity.config.security.CustomAuthenticationProvider;
import com.duynt.projectsecurity.config.security.JWTUserDetailService;
import com.duynt.projectsecurity.config.security.JwtTokenUtil;
import com.duynt.projectsecurity.entity.User;
import com.duynt.projectsecurity.model.request.JwtRequest;
import com.duynt.projectsecurity.model.request.SignupRequest;
import com.duynt.projectsecurity.model.response.JwtResponse;
import com.duynt.projectsecurity.service.UserDetailsImpl;
import com.duynt.projectsecurity.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthController {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JWTUserDetailService jwtUserDetailService;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthencationToken(@Valid @RequestBody JwtRequest user) {
        customAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        UserDetailsImpl userDetails = jwtUserDetailService.loadUserByUsername(user.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> regist(@Valid @RequestBody SignupRequest user) {
        User newUser = userService.save(new User(user.getUsername(), user.getEmail(), encoder.encode(user.getPassword())));
        return ResponseEntity.ok(newUser);
    }
}
