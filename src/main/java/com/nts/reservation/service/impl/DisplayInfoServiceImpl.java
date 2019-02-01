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

	@Override
	public List<CommentDto> getCommentList(Long displayInfoId) {
		return commentDao.selectComments(displayInfoId);
	}

	@Override
	public List<CommentImageDto> getCommentImageList(Long reservationUserCommentId) {
		return commentImageDao.selectCommentImages(reservationUserCommentId);
	}

	@Override
	public DisplayInfoDto getDisplayInfo(Long displayInfoId) {
		return displayInfoDao.selectDisplayInfo(displayInfoId);
	}

	@Override
	public DisplayInfoImageDto getDisplayInfoImage(Long displayInfoId) {
		return displayInfoImageDao.selectDisplayInfoImage(displayInfoId);
	}

	@Override
	public List<ProductImageDto> getProductImageList(Long productId) {
		return productImageDao.selectProductImages(productId);
	}

	@Override
	public List<ProductPriceDto> getProductPriceList(Long productId) {
		return productPriceDao.selectProductPrices(productId);
	}

}
