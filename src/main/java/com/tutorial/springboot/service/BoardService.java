package com.tutorial.springboot.service;

import com.tutorial.springboot.domain.Reply;
import com.tutorial.springboot.domain.board.Board;
import com.tutorial.springboot.domain.board.BoardRepository;
import com.tutorial.springboot.domain.reply.ReplyRepository;
import com.tutorial.springboot.domain.user.User;
import com.tutorial.springboot.domain.user.UserRepository;
import com.tutorial.springboot.dto.ReplySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

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
        Board board = boardRepository.findById(id).orElseThrow(()-> {
            return new IllegalArgumentException("해당 글이 존재하지 않습니다.");
        });
//        System.out.println("======= id : " + board.getUser().getId() + user.getId());
        if (board.getUser().getId() != user.getId()) {
            throw new IllegalStateException("삭제 권한이 없습니다.");
        }
        boardRepository.delete(board);
    }

    public void 글수정하기(int id, Board new_board, User user) {
        Board board = boardRepository.findById(id).orElseThrow(()-> {
            return new IllegalArgumentException("해당 글이 존재하지 않습니다.");
        });

        if (board.getUser().getId() != user.getId()) {
            throw new IllegalStateException("수정 권한이 없습니다.");
        }
        board.setTitle(new_board.getTitle());
        board.setContent(new_board.getContent());
//        boardRepository.save(board);
    }

    @Transactional
    public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {

        replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());

//        User user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(()->{
//            return new IllegalArgumentException("댓글 쓰기 실패 : 올바른 유저가 아닙니다.");
//        }); // 영속화 완료
//
//        Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(()->{
//            return new IllegalArgumentException("댓글 쓰기 실패 : 게시글을 찾을 수 없습니다.");
//        }); // 영속화 완료
//
//        Reply reply = Reply.builder()
//                .user(user)
//                .board(board)
//                .content(replySaveRequestDto.getContent())
//                .build();
//
////        Reply reply = new Reply();
////        reply.update(user, board, replySaveRequestDto.getContent());
//
//        replyRepository.save(reply);
    }
}
