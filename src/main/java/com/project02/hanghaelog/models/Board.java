package com.project02.hanghaelog.models;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity //테이블임을 명시
@Getter
@NoArgsConstructor // 기본 생성자
public class Board extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO) // 아이디 시퀀스
    @Id
    private Long Bid;
    @Column(nullable = false)
    private String bTitle;
    @Column(nullable = false)
    private String bWriter;
    @Column(nullable = false)
    private String bContent;


    public Board(BoardRequestDto requestDto) {
        this.bTitle = requestDto.getBTitle();
        this.bWriter = requestDto.getBWriter();
        this.bContent = requestDto.getBContent();
    }

    public void boardUpdate(BoardRequestDto boardDto) {

        this.bTitle = boardDto.getBTitle();
        this.bWriter = boardDto.getBWriter();
        this.bContent = boardDto.getBContent();
    }


}
