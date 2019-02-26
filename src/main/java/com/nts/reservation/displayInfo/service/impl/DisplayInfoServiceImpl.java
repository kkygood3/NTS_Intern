/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.displayInfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.service.CommentService;
import com.nts.reservation.commons.validator.ArgumentValidator;
import com.nts.reservation.displayInfo.dao.DisplayInfoDao;
import com.nts.reservation.displayInfo.dto.DisplayInfo;
import com.nts.reservation.displayInfo.dto.DisplayInfoImage;
import com.nts.reservation.displayInfo.dto.DisplayInfoResponse;
import com.nts.reservation.displayInfo.service.DisplayInfoService;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;
import com.nts.reservation.product.service.ProductService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {
	@Autowired
	private DisplayInfoDao displayInfoDaoImpl;

	@Autowired
	private ProductService productServiceImpl;

	@Autowired
	private CommentService commentServiceImpl;

	@Override
	public DisplayInfo getDisplayInfo(int displayInfoId) {
		ArgumentValidator.checkDisplayInfoId(displayInfoId);

		return displayInfoDaoImpl.selectDisplayInfoByDisplayInfoId(displayInfoId);
	}

	@Override
	public DisplayInfoImage getDisplayInfoImage(int displayInfoId) {
		ArgumentValidator.checkDisplayInfoId(displayInfoId);

		return displayInfoDaoImpl.selectDisplayInfoImage(displayInfoId);
	}

	@Override
	public DisplayInfoResponse getDisplayInfoResponse(int displayInfoId, int start, int limit) {
		ArgumentValidator.checkDisplayInfoId(displayInfoId);
		ArgumentValidator.checkStartAndLimit(start, limit);

		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();

		List<ProductImage> productImages = productServiceImpl.getProductImage(displayInfoId);
		displayInfoResponse.setProductImages(productImages);

		List<ProductPrice> productPrices = productServiceImpl.getProductPrice(displayInfoId);
		displayInfoResponse.setProductPrices(productPrices);

		List<Comment> limitCommentList = commentServiceImpl.getLimitComment(displayInfoId, start, limit);
		displayInfoResponse.setComments(limitCommentList);

		DisplayInfo displayInfo = getDisplayInfo(displayInfoId);
		displayInfoResponse.setDisplayInfo(displayInfo);

		DisplayInfoImage displayInfoImage = getDisplayInfoImage(displayInfoId);
		displayInfoResponse.setDisplayInfoImage(displayInfoImage.getSaveFileName());

		List<Comment> allCommentList = commentServiceImpl.getAllComment(displayInfoId);
		double sumScore = 0.0;
		for (Comment comment : allCommentList) {
			sumScore = sumScore + comment.getScore();
		}
		double averageScore = 0.0;
		if (allCommentList.size() == 0) {
			averageScore = 0.0;
		} else {
			averageScore = sumScore / allCommentList.size();
		}
		displayInfoResponse.setAverageScore(averageScore);

		return displayInfoResponse;
	}

	@Override
	public DisplayInfo getDisplayInfoByReservationInfoId(int reservationInfoId) {
		ArgumentValidator.checkReservationInfoId(reservationInfoId);

		return displayInfoDaoImpl.selectDisplayInfoByReservationInfoId(reservationInfoId);
	}
}
