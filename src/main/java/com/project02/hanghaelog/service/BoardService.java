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
    public Board boardDetail(Long id) {
        return boardRepository.findById(id)

                .orElseThrow(()-> new IllegalArgumentException("id를 찾지 못했습니다."));
    }

}
