package com.sparta.anonymousboard.controller;

import com.sparta.anonymousboard.dto.BoardDeleteRequestDto;
import com.sparta.anonymousboard.dto.BoardRequestDto;
import com.sparta.anonymousboard.dto.BoardResponseDto;
import com.sparta.anonymousboard.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/boards")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto){
        return boardService.createBoard(requestDto);
    }

    @GetMapping("/boards")
    public List<BoardResponseDto> getBoards(){
        return boardService.getBoards();
    }

    @GetMapping("/boards/{id}")
    public BoardResponseDto getBoardByID(@PathVariable Long id){
        return boardService.getBoardByID(id);
    }

    @PutMapping("/boards/{id}")
    public ResponseEntity<Object> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){
        return boardService.updateBoard(id, requestDto);
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Object> deleteBoard(@PathVariable Long id, @RequestBody BoardDeleteRequestDto requestDto){
        return boardService.deleteBoard(id,requestDto);
    }
}
