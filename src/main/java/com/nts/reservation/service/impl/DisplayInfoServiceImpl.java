/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.CommentDao;
import com.nts.reservation.dao.CommentImageDao;
import com.nts.reservation.dao.DisplayInfoDao;
import com.nts.reservation.dao.DisplayInfoImageDao;
import com.nts.reservation.dao.ProductImageDao;
import com.nts.reservation.dao.ProductPriceDao;
import com.nts.reservation.dto.CommentDto;
import com.nts.reservation.dto.CommentImageDto;
import com.nts.reservation.dto.DisplayInfoDto;
import com.nts.reservation.dto.DisplayInfoImageDto;
import com.nts.reservation.dto.ProductImageDto;
import com.nts.reservation.dto.ProductPriceDto;
import com.nts.reservation.service.DisplayInfoService;

/**
 * @author 육성렬
 */
@Service
@Transactional(readOnly = true)
public class DisplayInfoServiceImpl implements DisplayInfoService {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private CommentImageDao commentImageDao;

	@Autowired
	private DisplayInfoDao displayInfoDao;

	@Autowired
	private DisplayInfoImageDao displayInfoImageDao;

	@Autowired
	private ProductImageDao productImageDao;

	@Autowired
	private ProductPriceDao productPriceDao;

	/**
	 * @desc 상품에 대한 댓글 리스트를 요청한다.
	 * @param displayInfoId
	 */
	@Override
	public List<CommentDto> getCommentList(Long displayInfoId) {
		return commentDao.selectComments(displayInfoId);
	}

	/**
	 * @desc 댓글에 대한 이미지 리스트를 요청한다..
	 * @param reservationUserCommentId
	 */
	@Override
	public List<CommentImageDto> getCommentImageList(Long reservationUserCommentId) {
		return commentImageDao.selectCommentImages(reservationUserCommentId);
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
		return displayInfoImageDao.selectDisplayInfoImage(displayInfoId);
	}

	/**
	 * @desc 상품에 대한 상품 이미지 리스트를 요청한다.
	 * @param productId
	 */
	@Override
	public List<ProductImageDto> getProductImageList(Long productId) {
		return productImageDao.selectProductImages(productId);
	}

	/**
	 * @desc 상품에 대한 상품 가격 정보 리스트를 요청한다.
	 * @param productId
	 */
	@Override
	public List<ProductPriceDto> getProductPriceList(Long productId) {
		return productPriceDao.selectProductPrices(productId);
	}

	/**
	 * @desc  display에 해당하는 상품의 코멘트 평점의 평균 값을 반환한다.
	 * @param displayInfoId
	 */
	@Override
	public float getCommentAvgScore(Long displayInfoId) {
		return commentDao.selectCommentAvgScore(displayInfoId);
	}
}
