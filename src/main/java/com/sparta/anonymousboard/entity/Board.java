package com.sparta.anonymousboard.entity;

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
    @Column(name = "author", nullable = false, length = 500)
    private String author;
    @Column(name = "password", nullable = false, length = 500)
    private String password;
    @Column(name = "content", nullable = false)
    private String content;
}
