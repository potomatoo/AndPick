package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	public Board findOneByBoardName(String boardName);

	public Board findOneByBoardId(long boardId);

	public List<Board> findAllByUserNo(long userNo);

	public Board findOneByBoardNameAndUserNo(String boardName, long userNo);

}
