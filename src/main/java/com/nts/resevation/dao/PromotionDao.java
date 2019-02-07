/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.resevation.dao;

import static com.nts.resevation.dao.PromotionDaoSqls.*;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.nts.resevation.dto.PromotionDto;

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
	public List<PromotionDto> selectLimitedList(int limit) {
		return jdbcTemplate.query(SELECT_LIMITED_LIST, Collections.singletonMap("limit", limit), rowMapper);
	}

}
