/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nts.reservation.dto.CategoryDto;

/**
 * 카테고리 Mapper
 * @author jinwoo.bae
 */
@Mapper
public interface CategoryMapper {
	List<CategoryDto> selectCategories(int limit);
}
