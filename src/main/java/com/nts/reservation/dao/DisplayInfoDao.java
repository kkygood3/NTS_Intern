/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.DisplayInfoDaoSqls.SELECT_DISPLAY_INFO_BY_ID;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.DisplayInfoDto;

/**
 * @author 육성렬
 */
@Repository
public class DisplayInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfoDto> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoDto.class);

	public DisplayInfoDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * @desc 아이디에 해당하는 DisplayInfo Row를 하나 쿼리한다.
	 * @param displayInfoId
	 * @return DisplayInfoDto
	 */
	public DisplayInfoDto selectDisplayInfo(Long displayInfoId) {
		Map<String, Object> map = Collections.singletonMap("id", displayInfoId);
		return jdbc.queryForObject(SELECT_DISPLAY_INFO_BY_ID, map, rowMapper);
	}
}
