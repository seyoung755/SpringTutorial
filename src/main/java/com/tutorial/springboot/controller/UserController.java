package com.tutorial.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("user/joinForm")
    public String joinform() {

        return "user/joinForm";
    }


    @GetMapping("user/loginForm")
    public String loginform() {

        return "user/loginForm";
    }
}
