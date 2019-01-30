/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.service;

import java.util.List;

import com.nts.dto.Category;


/**
 *
 * @description : Category Service Interface
 * @package : com.nts.service
 * @filename : CategoryService.java
 * @author : 최석현
 * @method : List<Category> getItems()
 * 
 */
public interface CategoryService {

	public List<Category> getItems();
	
}