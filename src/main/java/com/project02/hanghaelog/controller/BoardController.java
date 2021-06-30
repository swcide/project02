package com.project02.hanghaelog.controller;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
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
    @GetMapping("/api/board")
    public List<Board> getBoardList() {
        return boardRepository.findAllByStatusTrueOrderByCreatedAtDesc();
    }
    @GetMapping("/api/board/detail/{id}")
    public Board boardDetail(@PathVariable Long id) {
        Board b = boardRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("id를 찾지 못했습니다."));
        return b ;
    }
    @PutMapping("/api/board/update/{id}")
    public Long boardUpdate(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {

        boardService.update (id,requestDto);
        return id;
    }
    @PutMapping("/api/board/delete/{id}")
    public Long boardDelete(@PathVariable Long id,@RequestBody BoardRequestDto requestDto) {
        boardService.update (id,requestDto);
        return id;
    }
//    @DeleteMapping("/api/board/delete/{id}")
//    public Long deleteMemo(@PathVariable Long id) {
//        boardRepository.deleteById(id);
//        return id;
//    }

}
