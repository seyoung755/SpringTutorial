package com.tutorial.springboot.controller.api;

import com.tutorial.springboot.config.auth.PrincipalDetail;
import com.tutorial.springboot.domain.board.Board;
import com.tutorial.springboot.dto.ResponseDto;
import com.tutorial.springboot.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.글쓰기(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK, 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.글삭제하기(id, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK, 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.글수정하기(id, board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK, 1);
    }
 }