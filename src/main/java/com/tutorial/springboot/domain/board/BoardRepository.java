package com.tutorial.springboot.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<Board, Integer> {

}
