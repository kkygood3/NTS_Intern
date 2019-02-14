/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.DisplayInfoDao;
import com.nts.reservation.dto.CommentDto;
import com.nts.reservation.dto.CommentImageDto;
import com.nts.reservation.dto.DisplayInfoDto;
import com.nts.reservation.dto.DisplayInfoImageDto;
import com.nts.reservation.dto.ProductImageDto;
import com.nts.reservation.dto.ProductPriceDto;
import com.nts.reservation.dto.ProductPriceForRenderDto;
import com.nts.reservation.service.DisplayInfoService;

/**
 * @author 육성렬
 */
@Service
@Transactional(readOnly = true)
public class DisplayInfoServiceImpl implements DisplayInfoService {

	@Autowired
	private DisplayInfoDao displayInfoDao;

	/**
	 * @desc 상품에 대한 댓글 리스트를 요청한다.
	 * @param displayInfoId
	 */
	@Override
	public List<CommentDto> getCommentList(Long displayInfoId) {
		List<CommentDto> commentList = displayInfoDao.selectComments(displayInfoId);
		for (CommentDto comment : commentList) {
			List<CommentImageDto> commentImages = displayInfoDao.selectCommentImages(comment.getCommentId());
			comment.setCommentImages(commentImages);
		}
		return commentList;
	}

	/**
	 * @desc 상품에 대한 상품 상세 정보를 요청한다.
	 * @param displayInfoId
	 */
	@Override
	public DisplayInfoDto getDisplayInfo(Long displayInfoId) {
		return displayInfoDao.selectDisplayInfo(displayInfoId);
	}

	/**
	 * @desc 상품에 대한 상품 상세 정보 이미지를 요청한다.
	 * @param displayInfoId
	 */
	@Override
	public DisplayInfoImageDto getDisplayInfoImage(Long displayInfoId) {
		return displayInfoDao.selectDisplayInfoImage(displayInfoId);
	}

	/**
	 * @desc 상품에 대한 상품 이미지 리스트를 요청한다.
	 * @param productId
	 */
	@Override
	public List<ProductImageDto> getProductImageList(Long productId) {
		return displayInfoDao.selectProductImages(productId);
	}

	/**
	 * @desc 상품에 대한 상품 가격 정보 리스트를 요청한다.
	 * @param productId
	 */
	@Override
	public List<ProductPriceDto> getProductPriceList(Long productId) {
		return displayInfoDao.selectProductPrices(productId);
	}

	/**
	 * @desc  display에 해당하는 상품의 코멘트 평점의 평균 값을 반환한다.
	 * @param displayInfoId
	 */
	@Override
	public float getCommentAvgScore(Long displayInfoId) {
		return displayInfoDao.selectCommentAvgScore(displayInfoId);
	}

	/**
	 * @desc 티켓 가격 목록을 뷰에 렌더링하기 위한 데이터 조회
	 * @param productId
	 */
	@Override
	public List<ProductPriceForRenderDto> getProductPriceListForRender(Long productId) {
		return displayInfoDao.selectProductPricesForRender(productId);
	}

	/**
	 * @desc 첫번째 이미지를 메인 이미지로 하여 가져옴.
	 * @param productId
	 */
	@Override
	public ProductImageDto getProductMainImage(Long productId) {
		return displayInfoDao.selectMainProductImage(productId);
	}
}
