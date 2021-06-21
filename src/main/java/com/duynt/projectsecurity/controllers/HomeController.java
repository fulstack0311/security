package com.duynt.projectsecurity.controllers;

import com.duynt.projectsecurity.model.Home;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
public class HomeController {

    @RequestMapping("/home")
    public String home() {
        return "hello!";
    }

    @RequestMapping("/getHome")
    public Home getHome() {
        return new Home(1,"duynt","nguyen tien duy");
    }
}
