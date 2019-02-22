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

	/**
	 * 상품정보들과 상품총개수를 조합해 가져옵니다.
	 */
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

	/**
	 * 타입에 해당하는 상품이미지를 가져옵니다.
	 */
	@Override
	public ProductImageDto getProductImage(int productId, ImageType type) {
		return productMapper.selectProductImage(productId, type);
	}

	/**
	 * 전시상품정보와 상품이미지, response용 상품평들을 조합해 가져옵니다. 
	 */
	@Override
	public DetailResponseDto getDetailResponse(int productId, int displayInfoId, ImageType type, PageDto commnetPage) {
		DisplayInfoDto displayInfo = displayInfoMapper.selectDisplayInfo(displayInfoId);
		CommentResponseDto commentResponse = getCommentResponse(productId, commnetPage);
		String productImageUrl = productMapper.selectProductImage(productId, type).getSaveFileName();
		return new DetailResponseDto(displayInfo, commentResponse, productImageUrl);
	}

	/**
	 * 상품평들과 상품평들 개수, 평균점수를 조합해 가져옵니다.
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
	 * 예약하기 페이지 response용으로
	 * 전시상품정보와 상품가격들, 상품이미지, 상품최저가격, 예매일을 조합해 가져옵니다.
	 */
	@Override
	public ReserveResponseDto getReserveResponse(int productId, int displayInfoId, ImageType type) {
		DisplayInfoDto displayInfo = displayInfoMapper.selectDisplayInfo(displayInfoId);
		List<ProductPriceDto> productPrices = productMapper.selectProductPrices(productId);
		String productImageUrl = productMapper.selectProductImage(productId, type).getSaveFileName();
		return new ReserveResponseDto(displayInfo, productPrices, productImageUrl);
	}
}
