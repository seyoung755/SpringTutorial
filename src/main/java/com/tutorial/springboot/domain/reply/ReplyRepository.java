package com.tutorial.springboot.domain.reply;

import com.tutorial.springboot.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

}
