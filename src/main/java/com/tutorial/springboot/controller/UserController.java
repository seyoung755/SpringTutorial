package com.tutorial.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
// 그냥 주소가 /이면 saveForm.jsp 허용
// static이하에있는 /js /css /image 등

@Controller
public class UserController {

    @GetMapping("/auth/joinForm")
    public String joinform() {

        return "user/joinForm";
    }


    @GetMapping("/auth/loginForm")
    public String loginform() {

        return "user/loginForm";
    }
}
