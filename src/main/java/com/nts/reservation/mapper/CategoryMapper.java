/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nts.reservation.dto.CategoryDto;
import com.nts.reservation.dto.param.PageDto;

/**
 * 카테고리 Mapper
 * @author jinwoo.bae
 */
@Mapper
public interface CategoryMapper {
	/**
	 * 카테고리 정보들을 가져옵니다.
	 */
	List<CategoryDto> selectCategories(@Param("page") PageDto page);
}
