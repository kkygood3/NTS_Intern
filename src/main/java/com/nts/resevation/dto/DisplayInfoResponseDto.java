/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.resevation.dto;

import java.util.List;

/**
 * Response용 DTO
 * @author jinwoo.bae
 */
public class DisplayInfoResponseDto {
	DisplayInfoDto displayInfo;
	ProductImageDto productImage;
	DisplayInfoImageDto displayInfoImage;
	List<CommentDto> comments;
	double averageScore;
	List<ProductPriceDto> productPrices;
}
