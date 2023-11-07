package com.sparta.anonymousboard.service;

import com.sparta.anonymousboard.dto.*;
import com.sparta.anonymousboard.entity.Board;
import com.sparta.anonymousboard.exception.BoardException;
import com.sparta.anonymousboard.exception.ErrorMessage;
import com.sparta.anonymousboard.repository.BoardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    public List<BoardResponseDto> getBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc().stream().map(BoardResponseDto::new).toList();
    }

    public BoardResponseDto getBoardByID(Long id) {
        return new BoardResponseDto(findBoard(id));
    }

    @Transactional
    public ResponseEntity<Object> updateBoard(Long id, BoardRequestDto requestDto) {
        Board board = findBoard(id);
        if (validatePassword(requestDto, board).isEmpty()) {
            return createErrorResponse(ErrorMessage.PASSWORD_NOT_MATCH);
        }
        board.update(requestDto);
        return ResponseEntity.ok().body(new BoardResponseDto(board));
    }

    public ResponseEntity<Object> deleteBoard(Long id, BoardDeleteRequestDto requestDto) {
        Board board = findBoard(id);
        if (validatePassword(requestDto, board).isEmpty()) {
            return createErrorResponse(ErrorMessage.PASSWORD_NOT_MATCH);
        }
        boardRepository.delete(board);
        return ResponseEntity.ok(id);
    }

    private Board findBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(() ->
                new BoardException(ErrorMessage.BOARD_NOT_EXIST)
        );
    }

    private Optional<Board> validatePassword(RequestDto requestDto, Board board) {
        if (requestDto.getPassword().equals(board.getPassword())) {
            return Optional.of(board);
        }
        return Optional.empty();
    }

    private ResponseEntity<Object> createErrorResponse(ErrorMessage e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(e.getStatus(), e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(errorResponseDto);
    }
}
