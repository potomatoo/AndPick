package com.ssafy.model.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "board")
public class Board {
	@Id
	@Column(name = "board_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long boardId;
	@Column(name = "user_no")
	private long userNo;
	@Column(name = "board_name")
	private String boardName;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	private List<News> newsList;

	public long getBoardId() {
		return boardId;
	}

	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	@Override
	public String toString() {
		return "Board [boardId=" + boardId + ", userNo=" + userNo + ", boardName=" + boardName + ", newsList="
				+ newsList + "]";
	}
}
