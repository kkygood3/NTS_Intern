/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service;

import java.util.List;

import com.nts.dto.Promotion;


/**
 *
 * @description : Promotion Service Interface
 * @package : com.nts.service
 * @filename : PromotionService.java
 * @author : 최석현
 * @method : List<Promotion> getItems()
 * 
 */
public interface PromotionService {
	
	public List<Promotion> getItems();
	
}

