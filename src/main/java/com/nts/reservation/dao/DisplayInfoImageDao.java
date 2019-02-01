/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.DisplayInfoImageDaoSqls.SELECT_DISPLAY_INFO_IMAGE_BY_ID;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.DisplayInfoImageDto;

/**
 * @author 육성렬
 */
@Repository
public class DisplayInfoImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfoImageDto> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImageDto.class);

	public DisplayInfoImageDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * @desc 아이디 값에 해당하는 DisplayInfoImage 행하나를 쿼리한다.
	 * @param id
	 * @return DisplayInfoImageDto
	 */
	public DisplayInfoImageDto selectDisplayInfoImage(Long displayInfoId) {
		Map<String, Object> map = Collections.singletonMap("id", displayInfoId);
		return jdbc.queryForObject(SELECT_DISPLAY_INFO_IMAGE_BY_ID, map, rowMapper);
	}
}
