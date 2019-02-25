/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.displayinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.service.CommentService;
import com.nts.reservation.displayinfo.dao.DisplayInfoDao;
import com.nts.reservation.displayinfo.dto.DisplayInfo;
import com.nts.reservation.displayinfo.dto.DisplayInfoImage;
import com.nts.reservation.displayinfo.dto.DisplayInfoResponse;
import com.nts.reservation.displayinfo.service.DisplayInfoService;
import com.nts.reservation.product.dao.ProductDao;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {

	@Autowired
	private DisplayInfoDao displayInfoDaoImpl;

	@Autowired
	private CommentService commentServiceImpl;

	@Autowired
	private ProductDao productDaoImpl;

	@Override
	public DisplayInfoResponse getDisplayInfos(int displayInfoId, int count) {
		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();

		List<Comment> selectComments = commentServiceImpl.getComments(displayInfoId, count);
		double selectAverageScore;
		// 댓글이 없을 경우엔 average Score : 0 대입
		if (selectComments.isEmpty()) {
			selectAverageScore = 0;
		// 댓글이 있는 경우엔 average Score 가져오기
		} else {
			selectAverageScore = displayInfoDaoImpl.selectAverageScore(displayInfoId);
		}
		DisplayInfo selectDisplayInfo = displayInfoDaoImpl.selectDisplayInfoByDisplayInfoId(displayInfoId);
		DisplayInfoImage selectDisplayInfoImage = displayInfoDaoImpl.selectDisplayInfoImageByDisplayInfoId(displayInfoId);
		List<ProductImage> selectProductImages = productDaoImpl.selectProductImages(displayInfoId);
		List<ProductPrice> selectProductPrices = productDaoImpl.selectProductPrices(displayInfoId);
		int selectTotalComments = displayInfoDaoImpl.selectTotalcomments(displayInfoId);

		displayInfoResponse.setComments(selectComments);
		displayInfoResponse.setAverageScore(selectAverageScore);
		displayInfoResponse.setDisplayInfo(selectDisplayInfo);
		displayInfoResponse.setDisplayInfoImage(selectDisplayInfoImage);
		displayInfoResponse.setProductImages(selectProductImages);
		displayInfoResponse.setProductPrices(selectProductPrices);
		displayInfoResponse.setTotalComments(selectTotalComments);

		return displayInfoResponse;
	}
}
