/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.controller.api.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.dto.commentdto.Comment;
import com.nts.dto.displayinfodto.DisplayInfo;
import com.nts.dto.displayinfodto.DisplayInfoImage;
import com.nts.dto.displayinfodto.DisplayInfoResponse;
import com.nts.dto.productdto.Product;
import com.nts.dto.productdto.ProductImage;
import com.nts.dto.productdto.ProductPrice;
import com.nts.dto.productdto.ProductResponse;
import com.nts.exception.InvalidParameterException;
import com.nts.service.CommentService;
import com.nts.service.DisplayInfoService;
import com.nts.service.ProductService;

/**
 *
 * @description : Product API Controller
 * @package : com.nts.controller.api.product
 * @filename : ProductApiController.java
 * @author : 최석현
 * @method : ProductResponse products(int categoryId, int start)
 * 
 */
@RestController
@RequestMapping(path = "/api/products")
public class ProductApiController {
	@Autowired
	private ProductService productService;

	@Autowired
	private DisplayInfoService displayInfoService;

	@Autowired
	private CommentService commentService;

	/**
	 * @throws InvalidParameterException
	 * @description : Get 요청을 받으면 Service를 호출해 얻은 결과를 Response로 변환 후 return
	 */
	@GetMapping
	public ProductResponse products(@RequestParam(name = "categoryId") int categoryId,
			@RequestParam(name = "start", required = false, defaultValue = "0") int start) throws InvalidParameterException {

		if (categoryId < 0) {
			throw new InvalidParameterException("categoryId : " + categoryId);
		}
		if (start < 0) {
			throw new InvalidParameterException("start : " + start);
		}

		List<Product> items = productService.getItems(categoryId, start);
		int totalCount = productService.getCount(categoryId);

		ProductResponse productResponse = new ProductResponse(items, totalCount);

		return productResponse;
	}

	/**
	 * @throws InvalidParameterException
	 * @description : Get 요청을 받으면 Service를 호출해 얻은 결과를 Response로 변환 후 return
	 */
	@GetMapping("/{id}")
	public DisplayInfoResponse displayInfo(@PathVariable(name = "id") int displayInfoId)
			throws InvalidParameterException{
		
		if (displayInfoId <= 0) {
			throw new InvalidParameterException("displayInfoId : " + displayInfoId);
		}
		double averageScore = commentService.getAverageScoreByDisplayInfoId(displayInfoId);
		List<Comment> comments = commentService.getCommentsByDisplayInfoId(displayInfoId);
		DisplayInfo displayInfo = displayInfoService.getDisplayInfoByDisplayInfoId(displayInfoId);
		DisplayInfoImage displayInfoImage = displayInfoService.getDisplayInfoImageByDisplayInfoId(displayInfoId);
		List<ProductImage> productImages = productService.getProductImagesByDisplayInfoId(displayInfoId);
		List<ProductPrice> productPrices = productService.getProductPricesByDisplayInfoId(displayInfoId);
		
		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();
		displayInfoResponse.setAverageScore(averageScore);
		displayInfoResponse.setComments(comments);
		displayInfoResponse.setDisplayInfo(displayInfo);
		displayInfoResponse.setDisplayInfoImage(displayInfoImage);
		displayInfoResponse.setProductImages(productImages);
		displayInfoResponse.setProductPrices(productPrices);
		
		return displayInfoResponse;
	}
	
}