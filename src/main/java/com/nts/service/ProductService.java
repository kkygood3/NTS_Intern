/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.service;

import java.util.List;

import com.nts.dto.productdto.Product;
import com.nts.exception.ValidationException;


/**
 *
 * @description : Product Service Interface
 * @package : com.nts.service
 * @filename : ProductService.java
 * @author : 최석현
 * @method : List<Product> getItems(int categoryId, int start)
 * @method : int getCount(int categoryId)
 * 
 */
public interface ProductService {
	
	public List<Product> getItems(int categoryId, int start) throws ValidationException;
	public int getCount(int categoryId);
	
}

