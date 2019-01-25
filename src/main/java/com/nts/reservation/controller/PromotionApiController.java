package com.nts.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.Product;

@RestController
@RequestMapping(path = "/promotions")
public class PromotionApiController {
	@Autowired
	Product product;

	@GetMapping
	public List<Product> productGet() {
		return null;
	}

}
