package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.Board;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	public Board findOneByBoardName(String boardName);

	public Board findOneByBoardId(long boardId);

	public List<Board> findAllByUserNo(long userNo);

	@Modifying
	@Query(value = "delete from SSAFYDB.news where board_id=:board_id", nativeQuery = true)
	public void deleteNews(@Param("board_id") Long board_id);
}
