/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.Category;
import com.nts.reservation.dto.Comment;
import com.nts.reservation.dto.DisplayInfo;
import com.nts.reservation.dto.DisplayInfoImage;
import com.nts.reservation.dto.Product;
import com.nts.reservation.dto.ProductImage;
import com.nts.reservation.dto.ProductPrice;
import com.nts.reservation.dto.Reservation;
import com.nts.reservation.service.CategoryService;
import com.nts.reservation.service.DetailProductService;
import com.nts.reservation.service.ProductService;
import com.nts.reservation.service.ReservationService;

/**
* @author  : 이승수
*/
@RestController
@RequestMapping(path = "/api")
public class ReservationApiController {
	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	DetailProductService detailProductService;

	@Autowired
	ReservationService reservationService;

	@GetMapping(path = "/products")
	public Map<String, Object> getProducts(@RequestParam(name = "categoryId", required = false) Integer categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start) {
		List<Product> products = productService.getProducts(categoryId, start);
		int countProducts = 0;
		if (categoryId == null) {
			countProducts = productService.getCountAllProducts();
		} else {
			countProducts = productService.getCount(categoryId);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("items", products);
		map.put("totalCount", countProducts);

		return map;
	}

	@GetMapping(path = "/products/{displayInfoId}")
	public Map<String, Object> getDetail(@PathVariable("displayInfoId") Integer displayInfoId) {
		BigDecimal averageScore = detailProductService.getAverageScore(displayInfoId);
		List<Comment> comments = detailProductService.getComments(displayInfoId);
		DisplayInfo displayInfo = detailProductService.getDisplayInfo(displayInfoId);
		DisplayInfoImage displayInfoImage = detailProductService.getDisplayInfoImage(displayInfoId);
		List<ProductImage> productImages = detailProductService.getProductImages(displayInfoId);
		List<ProductPrice> productPrices = detailProductService.getProductPrices(displayInfoId);

		Map<String, Object> map = new HashMap<>();
		map.put("averageScore", averageScore);
		map.put("comments", comments);
		map.put("displayInfo", displayInfo);
		map.put("displayInfoImage", displayInfoImage);
		map.put("productImages", productImages);
		map.put("productPrices", productPrices);

		return map;
	}

	@GetMapping(path = "/promotions")
	public Map<String, Object> getPromotions() {
		List<Product> promotions = productService.getPromotions();
		int countProducts = productService.getCountPromotions();

		Map<String, Object> map = new HashMap<>();
		map.put("items", promotions);
		map.put("totalCount", countProducts);

		return map;
	}

	@GetMapping(path = "/categories")
	public Map<String, Object> getCategories() {
		List<Category> categories = categoryService.getCategories();

		Map<String, Object> map = new HashMap<>();
		map.put("items", categories);

		return map;
	}

	@PostMapping(path = "/reservations")
	public void serReservation(HttpSession session, @RequestBody Reservation reserveInfo) {
		reservationService.setReservation(session, reserveInfo);
	}
}
