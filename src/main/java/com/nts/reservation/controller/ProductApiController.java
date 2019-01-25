package com.nts.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.Product;
import com.nts.reservation.service.ProductService;

@RestController
@RequestMapping(path = "/promotions")
public class ProductApiController {
	@Autowired
	ProductService productService;

	@GetMapping
	public List<Product> promotionList() {
		System.out.println(productService.getPromotions());
		return productService.getPromotions();
	}

}
