package com.sparta.anonymousboard.service;

import com.sparta.anonymousboard.dto.BoardRequestDto;
import com.sparta.anonymousboard.dto.BoardResponseDto;
import com.sparta.anonymousboard.entity.Board;
import com.sparta.anonymousboard.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardResponseDto createMemo(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        Board saveBoard = boardRepository.save(board);
        BoardResponseDto boardResponseDto = new BoardResponseDto(saveBoard);
        return boardResponseDto;
    }

    public List<BoardResponseDto> getBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc().stream().map(BoardResponseDto::new).toList();
    }
}
