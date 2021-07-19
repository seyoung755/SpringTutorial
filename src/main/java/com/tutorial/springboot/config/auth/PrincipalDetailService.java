package com.tutorial.springboot.config.auth;

import com.tutorial.springboot.domain.user.User;
import com.tutorial.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    // 스프링이 로그인 요청을 가로챌 때 username, password 변수 2개를 가로채는데
    // password는 알아서 함.
    // username이 DB에 있는지만 확인해서 return하면 된다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User Principal = userRepository.findByUsername(username)
                .orElseThrow(()->{
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다" + username);
                });
        return new PrincipalDetail(Principal); // 스프링 시큐리티에 유저 정보가 저장이 됨
    }
}
