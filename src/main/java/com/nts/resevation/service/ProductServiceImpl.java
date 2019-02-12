/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.resevation.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.resevation.dao.CommentDao;
import com.nts.resevation.dao.DisplayInfoDao;
import com.nts.resevation.dao.ProductDao;
import com.nts.resevation.dto.CommentDto;
import com.nts.resevation.dto.CommentResponseDto;
import com.nts.resevation.dto.DisplayInfoDto;
import com.nts.resevation.dto.DisplayInfoImageDto;
import com.nts.resevation.dto.DisplayInfoResponseDto;
import com.nts.resevation.dto.ProductDto;
import com.nts.resevation.dto.ProductImageDto;
import com.nts.resevation.dto.ProductResponseDto;

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
		if (categoryId == CATEGORY_TYPE_ALL) {
			count = productDao.selectCount();
		} else {
			count = productDao.selectCountByCategoryId(categoryId);
		}
		if (count == 0) {
			return new ProductResponseDto(Collections.<ProductDto>emptyList(), count);
		}

		List<ProductDto> products = Collections.<ProductDto>emptyList();
		if (categoryId == CATEGORY_TYPE_ALL) {
			products = productDao.selectAllPaging(start, limit);
		} else {
			products = productDao.selectByCategoryIdPaging(categoryId, start, limit);
		}

		return new ProductResponseDto(products, count);
	}

	@Override
	public DisplayInfoResponseDto getDisplayInfoResponse(int displayInfoId, int productImageLimit) {
		DisplayInfoDto displayInfo = displayInfoDao.selectDisplayInfo(displayInfoId);
		List<ProductImageDto> productImages = productDao.selectProductImages(displayInfo.getProductId(),
			productImageLimit);
		DisplayInfoImageDto displayInfoImage = displayInfoDao.selectDisplayInfoImage(displayInfoId);

		return new DisplayInfoResponseDto(displayInfo, displayInfoImage, productImages);
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
}
