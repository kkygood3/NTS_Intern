/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nts.reservation.constant.ImageType;
import com.nts.reservation.dto.ProductDto;
import com.nts.reservation.dto.ProductImageDto;
import com.nts.reservation.dto.ProductPriceDto;

/**
 * 상품정보 Mapper
 * @author jinwoo.bae
 */
@Mapper
public interface ProductMapper {
	/**
	 * 특정 id에 해당하는 상품을 가져온다
	 */
	ProductDto selectProduct(int productId);

	/**
	 * 상품들을 가져온다 
	 */
	List<ProductDto> selectProducts(@Param("start") int start, @Param("limit") int limit);

	/**
	 * 특정 카테고리의 상품들을 가져온다.
	 */
	List<ProductDto> selectProductsByCategoryId(@Param("categoryId") int categoryId, @Param("start") int start,
		@Param("limit") int limit);

	/**
	 * 상품들의 총 개수를 가져온다 
	 */
	int selectProductCount();

	/**
	 * 특정 카테고리의 총 개수를 가져온다.
	 */
	int selectProductCountByCategoryId(int categoryId);

	/**
	 * 특정 id, type에 해당하는 프로덕트 이미지를 가져온다.
	 */
	ProductImageDto selectProductImage(@Param("productId") int productId, @Param("type") ImageType type);

	/**
	 * 특정 id에 해당하는 상품 가격들을 가져온다 
	 */
	List<ProductPriceDto> selectProductPrices(int productId);

}
