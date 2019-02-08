/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.dao.product.ProductRepository;
import com.nts.dto.product.Product;
import com.nts.dto.product.Products;
import com.nts.exception.ProductParamException;
import com.nts.util.CheckProductParameter;
import com.nts.service.product.ProductService;

/**
 * @author 전연빈
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	private static final int SELECT_PRODUCTS_ALL = 0;

	/**
	 * @desc 카테고리 별 product 가져오기
	 * @param categoryId
	 * @param start
	 * @return products( items [product list] , totalCount [카테고리별 총 개수] )
	 * @throws ProductParamException 
	 */
	@Override
	public Products getProducts(int categoryId, int start) throws ProductParamException {

		Products products = new Products();

		if (categoryId != SELECT_PRODUCTS_ALL) {
			products.setItems(getProductsByCategory(categoryId, start));
			products.setTotalCount(getProductsCountByCategory(categoryId));
		} else {
			products.setItems(getProductsAll(start));
			products.setTotalCount(getProductsAllCount());
		}
		return products;
	}

	/**
	 * @desc 상품 전체 가져오기 ( 페이징 별 )
	 * @param start
	 * @return product list
	 */
	private List<Product> getProductsAll(int start) throws ProductParamException {

		if (CheckProductParameter.isInvalidStart(start)) {
			throw new ProductParamException("start = " + start);
		}

		return productRepository.selectProducts(start);
	}

	/**
	 * @desc 상품 전체 갯수
	 * @return count
	 */
	private int getProductsAllCount() {

		return productRepository.selectProductsCount();
	}

	/**
	 * @desc 카테고리 별 상품 리스트 가져오기 ( 페이징 별 )
	 * @return product list
	 */
	private List<Product> getProductsByCategory(int categoryId, int start) throws ProductParamException {

		if (CheckProductParameter.isInvalidStart(start)) {
			throw new ProductParamException("start = " + start);
		}

		return productRepository.selectProductsByCategory(categoryId, start);
	}

	/**
	 * @desc category Id 별 상품 갯수
	 * @param categoryId
	 * @return count
	 */
	private int getProductsCountByCategory(int categoryId) {

		return productRepository.selectProductsCountByCategory(categoryId);
	}

}
