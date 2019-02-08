/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.nts.reservation.service.PromotionService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class PromotionApiController {
	@Autowired
	PromotionService promotionService;
}
