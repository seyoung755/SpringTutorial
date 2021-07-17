package com.tutorial.springboot.costutorial;

import com.tutorial.springboot.domain.user.Role;
import com.tutorial.springboot.domain.user.User;
import com.tutorial.springboot.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // 데이터베이스에 없는 경우 null이 되므로 return null을 방지하고자 Optional로 감싸져있음.
        // 그러니 null인지 아닌지 판단해서 return 해라.
        // 람다식으로 바꿀 수 있다.
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다 : "+id);
            }
        });
        // MessageConverter가 자바 오브젝트를 응답하면 Jackson 라이브러리를 호출하여 JSON으로 변환
        return user;
    }

    @PostMapping("/dummy/join")
    public String Join(User user) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());
        System.out.println(user.getCreateDate());

        user.setRole(Role.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다";
    }
}
