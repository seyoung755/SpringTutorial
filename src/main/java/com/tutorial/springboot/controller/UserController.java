package com.tutorial.springboot.controller;

import com.tutorial.springboot.config.auth.PrincipalDetail;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

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
