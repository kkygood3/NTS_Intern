/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.displayInfo.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
	public DisplayInfos getDisplayInfosByDisplayInfoId(int displayInfoId) throws DisplayInfoNullException {
		
		DisplayInfos displayInfos = new DisplayInfos();

		try {

			displayInfos.setDisplayInfo(displayInfoRepository.selectDisplayInfoByDisplayInfoId(displayInfoId));
		} catch(EmptyResultDataAccessException e) {
			
			e.printStackTrace();
			throw new DisplayInfoNullException("displayInfoId = "+displayInfoId);
		}
		
		
		displayInfos.setDisplayInfoImage(displayInfoRepository.selectDisplayInfoImageByDisplayInfoId(displayInfoId));
		
		int productId = displayInfos.getDisplayInfo().getProductId();
		
		displayInfos.setProductImages(productRepository.selectProductImagesByProductId(productId));
		displayInfos.setProductPrices(productRepository.selectProductPricesByProductId(productId));
		
		displayInfos.setAverageScore(commentService.getAverageScoreByProductId(productId));
		displayInfos.setComments(commentService.getCommentsByProductId(productId));
		
		return displayInfos;
	}
	
}
