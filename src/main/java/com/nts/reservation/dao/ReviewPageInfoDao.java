package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.ReviewPageInfoDaoSqls.SELECT_BY_DISPLAY_INFO_ID;
import static com.nts.reservation.property.Const.DISPLAY_INFO_ID;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import com.nts.reservation.dto.ReviewPageInfo;

public class ReviewPageInfoDao extends BaseDao{
	RowMapper<ReviewPageInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReviewPageInfo.class);
	ReviewPageInfoDao(DataSource dataSource) {
		super(dataSource);
	}
	public ReviewPageInfo selectByDisplayInfoId(long displayInfoId) {
		Map<String, Object> params = new HashMap<>();
		params.put(DISPLAY_INFO_ID, displayInfoId);
		return getJdbc().queryForObject(SELECT_BY_DISPLAY_INFO_ID, params, rowMapper);
	}
}
