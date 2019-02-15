package com.nts.reservation.display.dao.daoImpl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.display.dao.DisplayDao;
import com.nts.reservation.display.dao.sqls.DisplayDaoSqls;
import com.nts.reservation.display.dto.DisplayInfo;
import com.nts.reservation.display.dto.DisplayInfoImage;

@Repository
public class DisplayDaoImpl implements DisplayDao {

	private NamedParameterJdbcTemplate jdbc;

	private RowMapper<DisplayInfo> displayInfoRowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	private RowMapper<DisplayInfoImage> displayInfoImageRowMapper = BeanPropertyRowMapper
		.newInstance(DisplayInfoImage.class);

	public DisplayDaoImpl(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public DisplayInfo selectDisplayInfo(int displayInfoId) {
		Map<String, Integer> param = new HashMap<>();
		param.put("displayInfoId", displayInfoId);
		return DataAccessUtils.singleResult(jdbc.query(DisplayDaoSqls.GET_DISPLAY_BY_ID, param, displayInfoRowMapper));
	}

	@Override
	public DisplayInfoImage selectDisplayInfoImage(int displayInfoId) {
		Map<String, Integer> param = new HashMap<>();
		param.put("displayInfoId", displayInfoId);
		return jdbc.queryForObject(DisplayDaoSqls.GET_DISPLAY_IMAGE_BY_ID, param, displayInfoImageRowMapper);
	}
}
