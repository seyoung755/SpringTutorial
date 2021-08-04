package com.tutorial.springboot.service;

import com.tutorial.springboot.domain.user.Role;
import com.tutorial.springboot.domain.user.User;
import com.tutorial.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional(readOnly = true)
    public User 회원찾기(String username) {
        User user = userRepository.findByUsername(username).orElseGet(()->
        {
            return new User();
        });
        return user;
    }

    @Transactional
    public void 회원가입(User user) {
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole(Role.USER);
        if (!userRepository.existsByUsername(user.getUsername())) {
            userRepository.save(user);
        }

    }

    @Transactional
    public void 회원수정(User user) {
        User persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
           return new IllegalArgumentException("해당 회원이 없습니다.");
        });
        if (persistance.getOauth() == null || persistance.getOauth().equals("")) {
            String rawPassword = user.getPassword();
            String encPassword = encoder.encode(rawPassword);
            persistance.setPassword(encPassword);
            persistance.setEmail(user.getEmail());
        }

    }

//    @Transactional(readOnly = true) // Select 시에도 트랜잭션을 시작해서 정합성 유지
//    public User 로그인(User user) {
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    }
}
