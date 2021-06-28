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
        return boardRepository.save(board);
    }
    @GetMapping("/api/memos")
    public List<Board> getMemos() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    @PutMapping("/api/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        boardService.boardUpdate(id, requestDto);
        return id;
    }


    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        boardRepository.deleteById(id);
        return id;
    }

}
