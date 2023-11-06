package com.sparta.anonymousboard.controller;

import com.sparta.anonymousboard.dto.BoardRequestDto;
import com.sparta.anonymousboard.dto.BoardResponseDto;
import com.sparta.anonymousboard.entity.Board;
import com.sparta.anonymousboard.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/boards")
    public BoardResponseDto createBoard(BoardRequestDto requestDto){
        Board board = new Board(requestDto);
        return boardService.createMemo(requestDto);
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
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){
        return boardService.updateBoard(id, requestDto);
    }

    @DeleteMapping("/boards/{id}")
    public Long deleteBoard(@RequestHeader("id") Long id, @RequestHeader("password") String password){
        return boardService.deleteBoard(id,password);
    }
}
