/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nts.reservation.constant.ImageType;
import com.nts.reservation.dto.PromotionDto;
import com.nts.reservation.dto.param.PageDto;

/**
 * 프로모션 Mapper
 * @author jinwoo.bae
 */
@Mapper
public interface PromotionMapper {
	/**
	 * 프로모션 정보들을 가져옵니다.
	 */
	List<PromotionDto> selectPromotions(@Param("type") ImageType type, @Param("page") PageDto page);
}
