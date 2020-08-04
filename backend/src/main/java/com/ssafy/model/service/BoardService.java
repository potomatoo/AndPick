package com.ssafy.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.User;
import com.ssafy.model.response.BasicResponse;

@Service
public interface BoardService {
	public BasicResponse svaeBoard(User user, String boardName);

	public BasicResponse findAllBoard(User user);

	public BasicResponse findOneBoard(User user, long boardId);

	public BasicResponse updateBoard(User user, long boardId, String boardName);

	public BasicResponse deleteBoard(User user, long boardId);

}
