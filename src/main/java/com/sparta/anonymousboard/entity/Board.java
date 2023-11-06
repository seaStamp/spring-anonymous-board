package com.sparta.anonymousboard.entity;

import com.sparta.anonymousboard.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board")
@NoArgsConstructor
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false, length = 500)
    private String title;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "content", nullable = false, length = 500)
    private String content;

    public Board(BoardRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
        this.content = requestDto.getContent();
    }

    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
    }
}
