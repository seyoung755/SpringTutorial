package com.tutorial.springboot.service;

import com.tutorial.springboot.domain.user.Role;
import com.tutorial.springboot.domain.user.User;
import com.tutorial.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void 회원가입(User user) {
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole(Role.USER);
        userRepository.save(user);
    }

//    @Transactional(readOnly = true) // Select 시에도 트랜잭션을 시작해서 정합성 유지
//    public User 로그인(User user) {
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    }
}
