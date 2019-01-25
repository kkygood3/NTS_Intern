/**
* Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.dao;

import static com.nts.reservationservice.dao.DisplayInfoDaoSqls.SELECT_DISPLAY_INFO_BY_PRODUCT_ID;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservationservice.dto.DisplayInfoDto;

@Repository
public class DisplayInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfoDto> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoDto.class);

	public DisplayInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<DisplayInfoDto> selectDisplayInfoByProductId(Long productId) {
		Map<String, ?> params = Collections.singletonMap("id", productId);
		return jdbc.query(SELECT_DISPLAY_INFO_BY_PRODUCT_ID, params, rowMapper);
	}
}
