/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.displayinfo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.comment.dao.CommentDao;
import com.nts.reservation.displayinfo.dao.DisplayInfoDao;
import com.nts.reservation.displayinfo.dto.DisplayInfoResponse;
import com.nts.reservation.displayinfo.service.DisplayInfoService;
import com.nts.reservation.product.dao.ProductDao;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {

	@Autowired
	private DisplayInfoDao displayInfoDaoImpl;

	@Autowired
	private CommentDao commentDaoImpl;

	@Autowired
	private ProductDao productDaoImpl;

	@Override
	public DisplayInfoResponse getDisplayInfos(int displayInfoId) {
		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();

		displayInfoResponse.setComments(commentDaoImpl.selectCommentByDisplayInfoId(displayInfoId));
		// 댓글이 없을 경우엔 0 대입
		if (displayInfoResponse.getComments().isEmpty()) {
			displayInfoResponse.setAverageScore(0);
		// 댓글이 있으면 Average값 가져오기.
		} else {
			displayInfoResponse.setAverageScore(displayInfoDaoImpl.selectAverageScore(displayInfoId));
		}
		displayInfoResponse.setDisplayInfo(displayInfoDaoImpl.selectDisplayInfoByDisplayInfoId(displayInfoId));
		displayInfoResponse.setDisplayInfoImage(displayInfoDaoImpl.selectDisplayInfoImageByDisplayInfoId(displayInfoId));
		displayInfoResponse.setProductImages(productDaoImpl.selectProductImagesByDisplayInfoId(displayInfoId));
		displayInfoResponse.setProductPrices(productDaoImpl.selectProductPricesByDisplayInfoId(displayInfoId));

		return displayInfoResponse;
	}
}
