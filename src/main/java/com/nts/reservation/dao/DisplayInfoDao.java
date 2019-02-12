package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.DisplayInfoDaoSqls.SELECT_BY_ID;
import static com.nts.reservation.property.Const.DISPLAY_INFO_ID;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.DisplayInfo;

@Repository
public class DisplayInfoDao extends BaseDao {

	RowMapper<DisplayInfo> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);

	public DisplayInfoDao(DataSource dataSource) {
		super(dataSource);
	}

	public DisplayInfo selectById(long displayInfoId) {
		Map<String, Long> params = new HashMap<>();
		params.put(DISPLAY_INFO_ID, displayInfoId);
		return getJdbc().queryForObject(SELECT_BY_ID, params, rowMapper);
	}
}
