package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.DatailPageInfoDaoSqls.SELECT_BY_DISPLAY_INFO_ID;
import static com.nts.reservation.property.Const.DISPLAY_INFO_ID;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import com.nts.reservation.dto.DatailPageInfo;

public class DatailPageInfoDao extends BaseDao {
	RowMapper<DatailPageInfo> rowMapper = BeanPropertyRowMapper.newInstance(DatailPageInfo.class);
	DatailPageInfoDao(DataSource dataSource) {
		super(dataSource);
	}

	public DatailPageInfo selectDetailPageInfo(long displayInfoId) {
		Map<String, Long> params = new HashMap<>();
		params.put(DISPLAY_INFO_ID, displayInfoId);
		return getJdbc().queryForObject(SELECT_BY_DISPLAY_INFO_ID, params, rowMapper);
	}
}
