package com.tutorial.springboot.costutorial;

import com.tutorial.springboot.domain.user.Role;
import com.tutorial.springboot.domain.user.User;
import com.tutorial.springboot.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired
    private UserRepository userRepository;

    // email, password 변경
    @DeleteMapping("/dummy/user/{id}")
    public String deleteUser(@PathVariable int id) {

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            return "존재하지 않는 사용자입니다";
        }

        return "삭제가 완료되었습니다";
    }

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
        String email = requestUser.getEmail();
        String password = requestUser.getPassword();

        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패하였습니다");
        });

        user.setEmail(email);
        user.setPassword(password);

        // save함수는 id에 해당하는 데이터가 없거나 id를 전달받지 않으면 insert를 하고
        // 있는 경우 update 쿼리를 날린다.

        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    // 한 페이지당 2건에 데이터를 리턴받아 볼 예정
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);
        List<User> users = pagingUser.getContent();

        return users;
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // 데이터베이스에 없는 경우 null이 되므로 return null을 방지하고자 Optional로 감싸져있음.
        // 그러니 null인지 아닌지 판단해서 return 해라.
        // 람다식으로 바꿀 수 있다.
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다 : " + id);
            }
        });
        // MessageConverter가 자바 오브젝트를 응답하면 Jackson 라이브러리를 호출하여 JSON으로 변환
        return user;
    }

    @PostMapping("/dummy/join")
    public String Join(User user) {

        user.setRole(Role.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다";
    }
}
