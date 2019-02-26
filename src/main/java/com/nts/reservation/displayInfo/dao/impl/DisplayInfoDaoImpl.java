/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.displayInfo.dao.impl;

import static com.nts.reservation.displayInfo.dao.query.DisplayInfoQuerys.*;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.displayInfo.dao.DisplayInfoDao;
import com.nts.reservation.displayInfo.dto.DisplayInfo;
import com.nts.reservation.displayInfo.dto.DisplayInfoImage;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Repository
public class DisplayInfoDaoImpl implements DisplayInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfo> displayInfoRowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	private RowMapper<DisplayInfoImage> displayInfoImageRowMapper = BeanPropertyRowMapper
		.newInstance(DisplayInfoImage.class);

	public DisplayInfoDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public DisplayInfo selectDisplayInfo(int displayInfoId) {
		Map<String, Integer> param = new HashMap<>();
		param.put("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_DISPLAY_INFO, param, displayInfoRowMapper);
	}

	@Override
	public DisplayInfoImage selectDisplayInfoImage(int displayInfoId) {
		Map<String, Integer> param = new HashMap<>();
		param.put("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_DISPLAY_INFO_IMAGE, param, displayInfoImageRowMapper);
	}

}
