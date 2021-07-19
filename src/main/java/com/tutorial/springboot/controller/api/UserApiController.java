package com.tutorial.springboot.controller.api;

import com.tutorial.springboot.domain.user.Role;
import com.tutorial.springboot.domain.user.User;
import com.tutorial.springboot.dto.ResponseDto;
import com.tutorial.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;


    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        userService.회원가입(user);
        return new ResponseDto<Integer> (HttpStatus.OK, 1);
    }



    // 다음 시간에 스프링 시큐리티 이용
//    @PostMapping("/api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//        System.out.println("UserApiController : login호출됨");
//        User principal = userService.로그인(user);
//
//        if(principal != null) {
//            session.setAttribute("principal", principal);
//        }
//        return new ResponseDto<Integer> (HttpStatus.OK, 1);
//    }
}
