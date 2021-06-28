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
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String content;


    public Board(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public Board(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.writer = requestDto.getWriter();
        this.content = requestDto.getContent();
    }



}
