package com.project02.hanghaelog.controller;

import com.project02.hanghaelog.models.Board;
import com.project02.hanghaelog.models.BoardRepository;
import com.project02.hanghaelog.models.BoardRequestDto;
import com.project02.hanghaelog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;


    @PostMapping("/api/board")
    public Board Boardwrite(@RequestBody BoardRequestDto requestDto) {

        Board board = new Board(requestDto);
        Board b = new Board();
        return boardRepository.save(board);
    }
    @GetMapping("/api/board")
    public List<Board> getBoardList() {
        return boardRepository.findAllByOrderByIdDesc();
    }


    @GetMapping("/api/board/detail/{id}")
    public Board boardDetail(@PathVariable Long id) {

        Board b = boardService.boardDetail(id);
        return b ;

    }

}
