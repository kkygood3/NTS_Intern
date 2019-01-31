package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.ThumbnailInfoDaoSqls.SELECT_ALL_WITH_PAGING;
import static com.nts.reservation.dao.sql.ThumbnailInfoDaoSqls.SELECT_BY_CATERORY_WITH_PAGING;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Category;
import com.nts.reservation.dto.ThumbnailInfo;

@Repository
public class ThumbnailInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ThumbnailInfo> rowMapper = BeanPropertyRowMapper.newInstance(ThumbnailInfo.class);

	public ThumbnailInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ThumbnailInfo> selectAllCategoris(Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);

		return jdbc.query(SELECT_ALL_WITH_PAGING, params, rowMapper);
	}

	public List<ThumbnailInfo> selectByCategory(Category category, Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", category.getId());
		params.put("start", start);
		params.put("limit", limit);

		return jdbc.query(SELECT_BY_CATERORY_WITH_PAGING, params, rowMapper);
	}
}
