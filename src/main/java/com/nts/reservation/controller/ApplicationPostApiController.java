package com.nts.reservation.controller;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nts.reservation.dto.reservation.ReservationInput;
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

	public Map<String, Object> postReservation(@RequestParam("totalData") String input)
		throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		ReservationInput resInput = objectMapper.readValue(input, ReservationInput.class);
		System.out.println(resInput.toString());

		Map<String, Object> result = new HashMap<>();
		return result;
	}
}
