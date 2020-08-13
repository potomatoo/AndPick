package com.ssafy.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Scrap;
import com.ssafy.model.response.BasicResponse;

@Service
public interface ScrapService {
	public BasicResponse saveScrap(Scrap scrap);

	public BasicResponse loadScrap(Scrap scrap);

}
