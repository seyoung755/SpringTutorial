package com.tutorial.springboot.costutorial;

import com.tutorial.springboot.domain.Reply;
import com.tutorial.springboot.domain.board.Board;
import com.tutorial.springboot.domain.board.BoardRepository;
import com.tutorial.springboot.domain.reply.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReplyControllerTest {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @GetMapping("/test/board/{id}")
    public Board getBoard(@PathVariable int id) {
        return boardRepository.findById(id).get();
    }

    @GetMapping("/test/reply")
    public List<Reply> getBoard() {
        return replyRepository.findAll();
    }


}
