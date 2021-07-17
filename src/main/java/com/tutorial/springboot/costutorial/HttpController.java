package com.tutorial.springboot.costutorial;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpController {

    @GetMapping("/http/get")
    public String getTest(Member m) {
        return "get 요청 : " + m.getUsername() + m.getId();
    }
}
