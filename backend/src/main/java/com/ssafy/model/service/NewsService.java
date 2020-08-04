package com.ssafy.model.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.User;
import com.ssafy.model.response.BasicResponse;

@Service
public interface NewsService {

	public BasicResponse saveNews(User user, String newsTitle, String newsLink, Date newsDate, String newsDescription,
			long boardId);

	public BasicResponse deleteNews(User user, long newsId);

	public BasicResponse findAllNews(User user);

	public BasicResponse findAllByBoardId(User user, long boardId);

	public BasicResponse findOneById(User user, long newsId);

}
