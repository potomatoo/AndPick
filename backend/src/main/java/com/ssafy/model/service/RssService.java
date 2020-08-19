package com.ssafy.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Category;
import com.ssafy.model.dto.Feed;
import com.ssafy.model.dto.Rss;
import com.ssafy.model.dto.Subscribe;
import com.ssafy.model.dto.User;
import com.ssafy.model.response.BasicResponse;

@Service
public interface RssService {

	public BasicResponse findAll();

	public BasicResponse findByCategoryName(String categoryName);

	public BasicResponse findByRssName(String rssName);

	public BasicResponse saveRss(Rss rss, Category category);

	public BasicResponse findItemByFeed(User user, Feed feed);

	public BasicResponse findItemBySubscribe(User user, Subscribe subscribe);

	public BasicResponse findByCategoryNameLike(String categoryName);

	public BasicResponse findMain();

	public BasicResponse findCount();

}
