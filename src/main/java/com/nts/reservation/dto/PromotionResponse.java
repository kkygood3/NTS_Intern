/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto;

import java.util.List;

import lombok.Data;

@Data
public class PromotionResponse {
	List<Promotion> items;
}
