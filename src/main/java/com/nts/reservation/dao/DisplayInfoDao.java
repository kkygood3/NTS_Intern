/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.DisplayInfoDaoSqls.*;

import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.DisplayInfoDto;

/**
 * 전시된 상품들을 가져오는 Dao 클래스
 * @author jinwoo.bae
 */
@Repository
public class DisplayInfoDao extends BasicDao<DisplayInfoDto> {
	public DisplayInfoDao(DataSource dataSource) {
		super(dataSource);
		setRowMapper(DisplayInfoDto.class);
	}

	/**
	 * displayInfoId값을 가지는 전시 상품 정보를 가져옵니다.
	 */
	public DisplayInfoDto selectDisplayInfo(int displayInfoId) {
		return jdbcTemplate.queryForObject(SELECT_DISPLAY_INFO,
			Collections.singletonMap("displayInfoId", displayInfoId), rowMapper);
	}
}
