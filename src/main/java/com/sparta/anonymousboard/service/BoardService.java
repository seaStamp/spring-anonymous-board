package com.sparta.anonymousboard.service;

import com.sparta.anonymousboard.dto.BoardDeleteRequestDto;
import com.sparta.anonymousboard.dto.BoardRequestDto;
import com.sparta.anonymousboard.dto.BoardResponseDto;
import com.sparta.anonymousboard.entity.Board;
import com.sparta.anonymousboard.repository.BoardRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        Board saveBoard = boardRepository.save(board);
        return new BoardResponseDto(saveBoard);
    }

    public List<BoardResponseDto> getBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc().stream().map(BoardResponseDto::new).toList();
    }

    public BoardResponseDto getBoardByID(Long id) {
        return boardRepository.findById(id).map(BoardResponseDto::new).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }

    private Board findBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }

    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDto) {
        // 선택한 게시판이 DB에 존재하는지 확인
        Board board = findBoard(id);

        if (!requestDto.getPassword().equals(board.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }
        board.update(requestDto);
        return new BoardResponseDto(board);
    }

    public Long deleteBoard(Long id, BoardDeleteRequestDto requestDto) {
        Board board = findBoard(id);

        if (!requestDto.getPassword().equals(board.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        boardRepository.delete(board);

        return id;
    }
}
