/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto;

import lombok.Data;

/**
* @author  : 이승수
*/
@Data
public class Promotion {
	private Long displayInfoId;
	private Long productId;
	private String placeName;
	private String productContent;
	private String productDescription;
	private String productImageUrl;
}
