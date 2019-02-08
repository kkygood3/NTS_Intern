/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.displayInfo.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.dao.displayinfo.DisplayInfoRepository;
import com.nts.dao.product.ProductRepository;
import com.nts.dto.displayinfo.DisplayInfos;
import com.nts.exception.DisplayInfoNullException;
import com.nts.service.comment.CommentService;
import com.nts.service.displayInfo.DisplayInfoService;

/**
 * @author 전연빈
 */
@Service
public class DisplayInfoServiceImpl implements DisplayInfoService{

	@Autowired
	private DisplayInfoRepository displayInfoRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CommentService commentService;
	
	/**
	 * @desc 상품 상세 정보 가져오기
	 * @param displayInfoId
	 * @throws DisplayInfoNullException 
	 * @return displayInfos
	 */
	@Override
	public DisplayInfos getDisplayInfos(int displayInfoId) throws DisplayInfoNullException {
		
		if(displayInfoRepository.checkDisplayInfoIdIsNull(displayInfoId)) {
			throw new DisplayInfoNullException("displayInfoId = "+displayInfoId);
		}
		
		DisplayInfos displayInfos = new DisplayInfos();
		
		displayInfos.setDisplayInfo(displayInfoRepository.selectDisplayInfo(displayInfoId));
		displayInfos.setDisplayInfoImage(displayInfoRepository.selectDisplayInfoImage(displayInfoId));
		
		int productId = displayInfos.getDisplayInfo().getProductId();
		
		displayInfos.setProductImages(productRepository.selectProductImagesByProductId(productId));
		displayInfos.setProductPrices(productRepository.selectProductPricesById(productId));
		displayInfos.setAverageScore(commentService.getAverageScore(productId));
		displayInfos.setComments(commentService.getCommentsByProductId(productId));
		
		return displayInfos;
	}
	
}
