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
		return displayInfoDaoImpl.selectDisplayInfo(displayInfoId);
	}

	@Override
	public DisplayInfoImage getDisplayInfoImage(int displayInfoId) {
		return displayInfoDaoImpl.selectDisplayInfoImage(displayInfoId);
	}

	@Override
	public DisplayInfoResponse getDisplayInfoResponse(int displayInfoId, int start, int limit) {
		List<Comment> commentList = commentServiceImpl.getLimitComment(displayInfoId, start, limit);
		DisplayInfo displayInfo = getDisplayInfo(displayInfoId);
		DisplayInfoImage displayInfoImage = getDisplayInfoImage(displayInfoId);
		double sumScore = 0.0;
		for (Comment comment : commentList) {
			sumScore = sumScore + comment.getScore();
		}
		double averageScore = 0.0;
		if (commentList.size() == 0) {
			averageScore = 0.0;
		} else {
			averageScore = sumScore / commentList.size();
		}

		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();
		List<ProductImage> productImages = productServiceImpl.getProductImage(displayInfoId);
		displayInfoResponse.setProductImages(productImages);
		List<ProductPrice> productPrices = productServiceImpl.getProductPrice(displayInfoId);
		displayInfoResponse.setProductPrices(productPrices);
		displayInfoResponse.setComments(commentList);
		displayInfoResponse.setDisplayInfo(displayInfo);
		displayInfoResponse.setDisplayInfoImage(displayInfoImage.getSaveFileName());
		displayInfoResponse.setAverageScore(averageScore);

		return displayInfoResponse;
	}
}
