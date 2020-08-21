package com.ssafy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.model.dto.Scrap;
import com.ssafy.model.dto.User;
import com.ssafy.model.response.BasicResponse;
import com.ssafy.model.service.ScrapService;

@Controller
public class ScrapController {
	@Autowired
	private ScrapService scrapService;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@PostMapping("/api/public/scrap/save")
	public Object SaveScrap(@RequestBody Scrap scrap) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		if (scrap.getId() == null || scrap.getScrap() == null) {
			result.status = false;
			result.message = "필수 값을 입력하세요 ";
			response = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		Scrap scrapdto = new Scrap();
		scrapdto.setId(scrap.getId());
		scrapdto.setScrap(scrap.getScrap());
		result = scrapService.saveScrap(scrapdto);

		if (result.status) {
			result.message = "저장에 성공하엿습니다.";
			response = new ResponseEntity(result, HttpStatus.OK);
		} else {
			result.message = "저장에 실패하엿습니다.";
			response = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@GetMapping("/api/scrap/load")
	public Object LoadScrap(@RequestHeader("Authorization") String jwtToken, @RequestParam("key") String key) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		if (key == null) {
			result.status = false;
			result.message = "필수 값을 입력하세요 ";
			response = new ResponseEntity(result, HttpStatus.OK);
			return response;
		}

		Scrap scrapdto = new Scrap();
		scrapdto.setId(key);
		result = scrapService.loadScrap(scrapdto);

		if (result.status) {
			result.message = "불러오기에 성공 하엿습니다.";
			response = new ResponseEntity(result, HttpStatus.OK);
		} else {
			result.message = "불러오기에 실패 하엿습니다.";
			response = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

}
