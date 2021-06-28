package com.project02.hanghaelog.service;

import com.project02.hanghaelog.models.Board;
import com.project02.hanghaelog.models.BoardRepository;
import com.project02.hanghaelog.models.BoardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor // final임을 명시, 생성자 대신 만들기.
@Service
public class BoardService {

    private final BoardRepository boardRepository;


    @Transactional
    public  Long boardUpdate(Long id, BoardRequestDto boardDto){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        board.boardUpdate(boardDto);
        return board.getBid();
    }
}
