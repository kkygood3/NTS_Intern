/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.display.dto;

import java.util.List;

import com.nts.reservation.comment.dto.CommentResponse;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Builder
public class DisplayResponse {
	private CommentResponse commentResponse;
	private DisplayInfo displayInfo;
	private DisplayInfoImage displayInfoImage;
	private List<ProductImage> productImages;
	private List<ProductPrice> productPrices;
	
}
