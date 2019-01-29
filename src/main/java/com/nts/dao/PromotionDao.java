/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

import static com.nts.dao.PromotionDaoSqls.*;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nts.dto.PromotionDto;

/**
 * 프로모션 정보들을 가져오는 클래스
 * @author jinwoo.bae
 *
 */
@Repository
public class PromotionDao {
	private JdbcTemplate jdbcTemplate;
	private RowMapper<PromotionDto> rowMapper = BeanPropertyRowMapper.newInstance(PromotionDto.class);

	public PromotionDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 모든 프로모션 목록들을 fileInfo의 ImageUrl와 함께 가져온다
	 * @return
	 */
	public List<PromotionDto> selectAll() {
		return jdbcTemplate.query(SELECT_ALL, rowMapper);
	}

}
