package com.nts.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.Promotion;
import com.nts.reservation.service.PromotionService;

/*
 * 프로모션 이미지 이름 리턴
 * @author 시윤
 */
@RestController
@RequestMapping(path = "/promotion")
public class PromotionApiController {
	@Autowired
	private PromotionService promotionService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Promotion> getPromotions() {
		List<Promotion> savaFileNameList = promotionService.getPromotions();

		return savaFileNameList;
	}
}
