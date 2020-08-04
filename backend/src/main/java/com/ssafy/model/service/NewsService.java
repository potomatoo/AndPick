package com.ssafy.model.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Board;
import com.ssafy.model.dto.News;
import com.ssafy.model.dto.User;
import com.ssafy.model.response.BasicResponse;

@Service
public interface NewsService {

	public BasicResponse saveNews(User user, News nwes);

	public BasicResponse deleteNews(User user, News nwes);

	public BasicResponse findAllNews(User user);

	public BasicResponse findAllByBoardId(User user, Board board);

	public BasicResponse findOneById(User user, News nwes);

}
