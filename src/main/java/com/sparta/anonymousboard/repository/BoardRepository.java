package com.sparta.anonymousboard.repository;
import com.sparta.anonymousboard.dto.BoardResponseDto;
import com.sparta.anonymousboard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByCreatedAtDesc();
}
