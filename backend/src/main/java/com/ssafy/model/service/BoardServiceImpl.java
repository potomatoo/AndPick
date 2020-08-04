package com.ssafy.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Board;
import com.ssafy.model.dto.User;
import com.ssafy.model.repository.BoardRepository;
import com.ssafy.model.response.BasicResponse;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardRepository boardRepository;

	@Override
	public BasicResponse svaeBoard(User user, String boardName) {
		// TODO Auto-generated method stub
		BasicResponse response = new BasicResponse();

		if (user == null) {
			response.message = "잘못된 사용자 정보입니다.";
			response.status = false;
			return response;
		}

		if (boardName == null) {
			response.message = "보드 이름을 입력해 주세요";
			response.status = false;
			return response;
		}

		if (boardRepository.findOneByBoardName(boardName) != null) {
			response.message = "중복된 보드 이름입니다.";
			response.status = false;
			return response;
		}

		Board board = new Board();
		board.setUserNo(user.getUserNo());
		board.setBoardName(boardName);

		response.data = boardRepository.save(board);
		response.status = (response.data != null) ? true : false;
		if (response.status) {
			response.message = "보드 저장에 성공하였습니다.";
		} else {
			response.message = "보드 저장에 실패하였습니다.";
		}

		return response;
	}

	@Override
	public BasicResponse findAllBoard(User user) {
		// TODO Auto-generated method stub
		BasicResponse response = new BasicResponse();

		if (user == null) {
			response.message = "잘못된 사용자 정보입니다.";
			response.status = false;
			return response;
		}

		response.data = boardRepository.findAllByUserNo(user.getUserNo());
		response.status = (response.data != null) ? true : false;
		if (response.status) {
			response.message = "보드 조회에 성공하였습니다.";
		} else {
			response.message = "보드 조회에 실패하였습니다.";
		}

		return response;
	}

	@Override
	public BasicResponse findOneBoard(User user, long boardId) {
		// TODO Auto-generated method stub
		BasicResponse response = new BasicResponse();

		if (user == null) {
			response.message = "잘못된 사용자 정보입니다.";
			response.status = false;
			return response;
		}

		response.data = boardRepository.findOneByBoardId(boardId);
		response.status = (response.data != null) ? true : false;
		if (response.status) {
			response.message = "보드 조회에 성공하였습니다.";
		} else {
			response.message = "보드 조회에 실패하였습니다.";
		}

		return response;
	}

	@Override
	public BasicResponse updateBoard(User user, long boardId, String boardName) {
		// TODO Auto-generated method stub
		BasicResponse response = new BasicResponse();

		if (user == null) {
			response.message = "잘못된 사용자 정보입니다.";
			response.status = false;
			return response;
		}

		if (boardName == null) {
			response.message = "보드 이름을 입력해 주세요";
			response.status = false;
			return response;
		}

		Board board = boardRepository.findOneByBoardId(boardId);
		if (board == null) {
			response.message = "해당 보드가 없습니다.";
			response.status = false;
			return response;
		}

		board.setBoardName(boardName);
		response.data = boardRepository.save(board);

		response.status = (response.data != null) ? true : false;
		if (response.status) {
			response.message = "보드 수정에 성공하였습니다.";
		} else {
			response.message = "보드 수정에 실패하였습니다.";
		}

		return response;
	}

	@Override
	public BasicResponse deleteBoard(User user, long boardId) {
		// TODO Auto-generated method stub
		BasicResponse response = new BasicResponse();

		if (user == null) {
			response.message = "잘못된 사용자 정보입니다.";
			response.status = false;
			return response;
		}

		boardRepository.deleteById(boardId);

		response.status = true;
		if (response.status) {
			response.message = "보드 삭제에 성공하였습니다.";
		} else {
			response.message = "보드 삭제에 실패하였습니다.";
		}

		return response;
	}

}
