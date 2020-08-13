package com.ssafy.model.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Scrap;
import com.ssafy.model.repository.ScrapRepository;
import com.ssafy.model.response.BasicResponse;

import ch.qos.logback.core.status.Status;

@Service
public class ScrapServiceImpl implements ScrapService {

	@Autowired
	private ScrapRepository scrapRepository;

	@Override
	public BasicResponse saveScrap(Scrap scrap) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();
		if (scrapRepository.findById(scrap.getId()).isPresent()) {
			return result;
		}
		scrap = scrapRepository.save(scrap);

		result.data = scrap;
		result.status = (result.data != null) ? true : false;
		return result;
	}

	@Override
	public BasicResponse loadScrap(Scrap scrap) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		if (!scrapRepository.findById(scrap.getId()).isPresent()) {
			result.status = false;
			return result;
		}

		result.data = scrapRepository.findById(scrap.getId());
		scrapRepository.deleteById(scrap.getId());
		result.status = (result.data != null) ? true : false;
		return result;
	}

}
