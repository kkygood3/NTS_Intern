/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.displayinfo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.comment.dao.impl.CommentDaoImpl;
import com.nts.reservation.displayinfo.dao.impl.DisplayInfoDaoImpl;
import com.nts.reservation.displayinfo.dto.DisplayInfoResponse;
import com.nts.reservation.displayinfo.service.DisplayInfoService;
import com.nts.reservation.product.dao.impl.ProductDaoImpl;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {

	@Autowired
	private DisplayInfoDaoImpl displayInfoDaoImpl;

	@Autowired
	private CommentDaoImpl commentDaoImpl;

	@Autowired
	private ProductDaoImpl productDaoImpl;

	@Override
	public DisplayInfoResponse getDisplayInfos(int displayInfoId, int limit) {
		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();

		displayInfoResponse.setComments(commentDaoImpl.selectCommentByDisplayInfoIdByLimit(displayInfoId, limit));
		displayInfoResponse.setDisplayInfo(displayInfoDaoImpl.selectDisplayInfoByDisplayInfoId(displayInfoId));
		displayInfoResponse.setDisplayInfoImage(displayInfoDaoImpl.selectDisplayInfoImageByDisplayInfoId(displayInfoId));
		displayInfoResponse.setProductImages(productDaoImpl.selectProductImagesByDisplayInfoId(displayInfoId));
		displayInfoResponse.setProductPrices(productDaoImpl.selectProductPricesByDisplayInfoId(displayInfoId));

		return displayInfoResponse;
	}

}
