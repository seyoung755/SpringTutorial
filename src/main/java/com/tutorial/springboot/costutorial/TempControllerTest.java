package com.tutorial.springboot.costutorial;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

    @GetMapping("/temp/home")
    public String tempHome() {
        System.out.println("tempHome()");
        // Spring의 파일 리턴 기본경로 : src/main/resources/static
        // 즉, 파일 명을 적을 때 앞에 /를 붙여야 한다.
        return "/home.html";
    }

    @GetMapping("/temp/image")
    public String tempImage() {
        return "/a.jpg";
    }

    @GetMapping("/temp/jsp")
    public String tempJsp() {
        return "test";
    }
}
