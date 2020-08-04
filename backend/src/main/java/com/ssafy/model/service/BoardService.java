package com.ssafy.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Board;
import com.ssafy.model.dto.User;
import com.ssafy.model.response.BasicResponse;

@Service
public interface BoardService {
	public BasicResponse svaeBoard(User user, Board board);

	public BasicResponse findAllBoard(User user);

	public BasicResponse findOneBoard(User user, Board board);

	public BasicResponse updateBoard(User user, Board board);

	public BasicResponse deleteBoard(User user, Board board);

}
