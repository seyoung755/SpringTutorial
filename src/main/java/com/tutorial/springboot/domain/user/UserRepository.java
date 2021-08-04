package com.tutorial.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

// 해당 JPARepository는 User table을 관리하고, pk는 Integer라고 알려준다.
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}// JPA Naming 쿼리 -> 아래와 같은 규격으로 함수를 만들면 SELECT * FROM user WHERE username = ? AND password = ? 쿼리 동작
//    User findByUsernameAndPassword(String username, String password);

//    @Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//    User login(String username, String password);