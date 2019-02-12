/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.display.dto;

import java.util.List;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class DisplayResponse {
	private List<Comment> comments;
	private DisplayInfo displayInfo;
	private DisplayInfoImage displayInfoImage;
	private List<ProductImage> productImages;
	private List<ProductPrice> ProductPrices;
	
}
