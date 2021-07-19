package com.tutorial.springboot.service;

import com.tutorial.springboot.domain.board.Board;
import com.tutorial.springboot.domain.board.BoardRepository;
import com.tutorial.springboot.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;


    @Transactional
    public void 글쓰기(Board board, User user) {
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional
    public Page<Board> 글목록(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional
    public Board 글상세보기(int id) {
        return boardRepository.findById(id)
                .orElseThrow(()-> {
                    return new IllegalArgumentException("글 상세보기 실패 : 해당 글을 찾을 수 없습니다");
                });
    }

    @Transactional
    public void 글삭제하기(int id, User user) {
        if (id != user.getId()) {
            throw new IllegalStateException("삭제 권한이 없습니다");
        }
        boardRepository.deleteById(id);
    }
}
