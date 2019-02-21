/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.mapper;

import static com.nts.reservation.dao.sql.CategoryDaoSqls.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.nts.reservation.dto.CategoryDto;

/**
 * 
 * @author jinwoo.bae
 */
@Mapper
public interface CategoryMapper {
	@Select(SELECT_CATEGORIES)
	List<CategoryDto> selectCategories(@Param("limit") int limit);
}
