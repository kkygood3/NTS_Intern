/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import static com.nts.reservation.constant.ParameterDefaultValue.*;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.CommentDao;
import com.nts.reservation.dao.DisplayInfoDao;
import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.dto.CommentDto;
import com.nts.reservation.dto.DisplayInfoDto;
import com.nts.reservation.dto.ProductDto;
import com.nts.reservation.dto.ProductImageDto;
import com.nts.reservation.dto.ProductPriceDto;
import com.nts.reservation.dto.response.CommentResponseDto;
import com.nts.reservation.dto.response.DetailResponseDto;
import com.nts.reservation.dto.response.ProductImageResponseDto;
import com.nts.reservation.dto.response.ProductResponseDto;
import com.nts.reservation.dto.response.ReserveResponseDto;
import com.nts.reservation.service.ProductService;

/**
 * 상품 서비스 인터페이스 구현로직
 * @author jinwoo.bae
 */
@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private DisplayInfoDao displayInfoDao;
	@Autowired
	private CommentDao commentDao;

	@Override
	public ProductResponseDto getProductResponse(int categoryId, int start, int limit) {
		int count;
		if (categoryId == Integer.parseInt(CATEGORY_TYPE_ALL)) {
			count = productDao.selectCount();
		} else {
			count = productDao.selectCountByCategoryId(categoryId);
		}
		if (count == 0) {
			return new ProductResponseDto(Collections.<ProductDto>emptyList(), count);
		}

		List<ProductDto> products;
		if (categoryId == Integer.parseInt(CATEGORY_TYPE_ALL)) {
			products = productDao.selectProducts(start, limit);
		} else {
			products = productDao.selectProductsByCategoryId(categoryId, start, limit);
		}

		return new ProductResponseDto(products, count);
	}

	@Override
	public ProductImageResponseDto getProductImageResponse(int productId, int limit) {
		int count = productDao.selectProductImageCount(productId);
		if (count == 0) {
			return new ProductImageResponseDto(Collections.<ProductImageDto>emptyList(), count, "");
		}
		List<ProductImageDto> productImages = productDao.selectProductImages(productId, limit);
		String productDescription = productDao.selectProduct(productId).getProductDescription();
		return new ProductImageResponseDto(productImages, count, productDescription);
	}

	@Override
	public DetailResponseDto getDetailResponse(int productId, int displayInfoId, int commentLimit) {
		DisplayInfoDto displayInfo = displayInfoDao.selectDisplayInfo(displayInfoId);
		CommentResponseDto commentResponse = getCommentResponse(productId, 0, commentLimit);
		return new DetailResponseDto(displayInfo, commentResponse);
	}

	@Override
	public CommentResponseDto getCommentResponse(int productId, int start, int limit) {
		int count = commentDao.selectCommentCount(productId);
		if (count == 0) {
			return new CommentResponseDto(Collections.<CommentDto>emptyList(), count, 0);
		}
		List<CommentDto> comments = commentDao.selectComments(productId, start, limit);
		double averageScore = commentDao.selectCommentAvgScore(productId);
		return new CommentResponseDto(comments, count, averageScore);
	}

	@Override
	public ReserveResponseDto getReserveResponse(int productId, int displayInfoId) {
		DisplayInfoDto displayInfo = displayInfoDao.selectDisplayInfo(displayInfoId);
		List<ProductPriceDto> productPrices = productDao.selectProductPrices(productId);
		String productImageUrl = productDao.selectProductImagesByType(productId, "ma", 1).get(0).getSaveFileName();
		return new ReserveResponseDto(displayInfo, productPrices, productImageUrl);
	}

}
