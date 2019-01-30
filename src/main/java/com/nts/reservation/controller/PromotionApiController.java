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

@RestController
@RequestMapping(path = "/promotion")
public class PromotionApiController {
	@Autowired
	PromotionService promotionService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Promotion> list(@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "category_id", required = false, defaultValue = "0") int categoryId) {

		List<Promotion> savaFileNameList = promotionService.getPromotions();

		return savaFileNameList;
	}
}
