package com.sparta.anonymousboard.repository;
import com.sparta.anonymousboard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
