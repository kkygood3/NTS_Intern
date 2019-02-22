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

import com.nts.reservation.constant.ImageType;
import com.nts.reservation.dto.CommentDto;
import com.nts.reservation.dto.DisplayInfoDto;
import com.nts.reservation.dto.ProductDto;
import com.nts.reservation.dto.ProductImageDto;
import com.nts.reservation.dto.ProductPriceDto;
import com.nts.reservation.dto.param.PageDto;
import com.nts.reservation.dto.response.CommentResponseDto;
import com.nts.reservation.dto.response.DetailResponseDto;
import com.nts.reservation.dto.response.ProductResponseDto;
import com.nts.reservation.dto.response.ReserveResponseDto;
import com.nts.reservation.mapper.CommentMapper;
import com.nts.reservation.mapper.DisplayInfoMapper;
import com.nts.reservation.mapper.ProductMapper;
import com.nts.reservation.service.ProductService;

/**
 * 상품 서비스 인터페이스 구현로직
 * @author jinwoo.bae
 */
@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private DisplayInfoMapper displayInfoMapper;
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public ProductResponseDto getProductResponse(int categoryId, PageDto page) {
		int count;
		if (categoryId == Integer.parseInt(CATEGORY_TYPE_ALL)) {
			count = productMapper.selectProductCount();
		} else {
			count = productMapper.selectProductCountByCategoryId(categoryId);
		}
		if (count == 0) {
			return new ProductResponseDto(Collections.<ProductDto>emptyList(), count);
		}

		List<ProductDto> products;
		if (categoryId == Integer.parseInt(CATEGORY_TYPE_ALL)) {
			products = productMapper.selectProducts(page);
		} else {
			products = productMapper.selectProductsByCategoryId(categoryId, page);
		}

		return new ProductResponseDto(products, count);
	}

	@Override
	public ProductImageDto getProductImage(int productId, ImageType type) {
		return productMapper.selectProductImage(productId, type);
	}

	/**
	 * detail Page
	 */
	@Override
	public DetailResponseDto getDetailResponse(int productId, int displayInfoId, ImageType type, PageDto commnetPage) {
		DisplayInfoDto displayInfo = displayInfoMapper.selectDisplayInfo(displayInfoId);
		CommentResponseDto commentResponse = getCommentResponse(productId, commnetPage);
		String productImageUrl = productMapper.selectProductImage(productId, type).getSaveFileName();
		return new DetailResponseDto(displayInfo, commentResponse, productImageUrl);
	}

	/**
	 * comment
	 */
	@Override
	public CommentResponseDto getCommentResponse(int productId, PageDto page) {
		int count = commentMapper.selectCommentCount(productId);
		if (count == 0) {
			return new CommentResponseDto(Collections.<CommentDto>emptyList(), count, 0);
		}
		List<CommentDto> comments = commentMapper.selectComments(productId, page);
		double averageScore = commentMapper.selectCommentAvgScore(productId);
		return new CommentResponseDto(comments, count, averageScore);
	}

	/**
	 * reserver Page
	 */
	@Override
	public ReserveResponseDto getReserveResponse(int productId, int displayInfoId, ImageType type) {
		DisplayInfoDto displayInfo = displayInfoMapper.selectDisplayInfo(displayInfoId);
		List<ProductPriceDto> productPrices = productMapper.selectProductPrices(productId);
		String productImageUrl = productMapper.selectProductImage(productId, type).getSaveFileName();
		return new ReserveResponseDto(displayInfo, productPrices, productImageUrl);
	}
}
