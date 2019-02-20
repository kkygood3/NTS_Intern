/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.PromotionDaoSqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.nts.reservation.constant.ImageType;
import com.nts.reservation.dto.PromotionDto;

/**
 * 프로모션 정보들을 가져오는 클래스
 * @author jinwoo.bae
 *
 */
@Repository
public class PromotionDao extends BasicDao<PromotionDto> {
	public PromotionDao(DataSource dataSource) {
		super(dataSource);
		setRowMapper(PromotionDto.class);
	}

	/**
	 * 모든 프로모션 목록들을 fileInfo의 ImageUrl와 함께 가져온다
	 * @return
	 */
	public List<PromotionDto> selectPromotions(int limit, ImageType type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("limit", limit);
		params.put("type", type.getName());
		return jdbcTemplate.query(SELECT_PRPMOTIONS, params, rowMapper);
	}

}
