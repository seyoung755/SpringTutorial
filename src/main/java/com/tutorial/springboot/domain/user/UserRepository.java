package com.tutorial.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

// 해당 JPARepository는 User table을 관리하고, pk는 Integer라고 알려준다.
public interface UserRepository extends JpaRepository<User, Integer> {
}
