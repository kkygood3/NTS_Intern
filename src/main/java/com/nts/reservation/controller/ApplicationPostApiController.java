package com.nts.reservation.controller;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nts.reservation.dto.reservation.Price;
import com.nts.reservation.service.CategoryService;
import com.nts.reservation.service.DetailService;
import com.nts.reservation.service.ProductService;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

@RestController
@RequestMapping(path = "/api/", method = {RequestMethod.POST})
public class ApplicationPostApiController {
	@Autowired
	ProductService productService;
	@Autowired
	DetailService detailService;
	@Autowired
	CategoryService categoryService;

	@PostMapping(path = "/reservations", consumes = {"multipart/form-data"})

	public Map<String, Object> postReservation(@RequestParam("name") String name,
		@RequestParam("tel") String tel,
		@RequestParam("email") String email,
		@RequestParam("prices") String prices) throws JsonParseException, JsonMappingException, IOException {
		JSONArray jsonArray = new JSONArray(prices);
		ObjectMapper objectMapper = new ObjectMapper();
		List<Price> listPrice = objectMapper.readValue(prices, new TypeReference<List<Price>>() {});

		System.out.println(name + "\n" + tel + "\n" + email + "\n" + prices);
		System.out.println(listPrice);

		Map<String, Object> result = new HashMap<>();
		return result;
	}
}
