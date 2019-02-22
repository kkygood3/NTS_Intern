/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nts.reservation.dto.DisplayInfoDto;

/**
 * 전시정보 Mapper
 * @author jinwoo.bae
 */
@Mapper
public interface DisplayInfoMapper {
	/**
	 * 전시정보를 가져옵니다
	 */
	DisplayInfoDto selectDisplayInfo(int id);
}
