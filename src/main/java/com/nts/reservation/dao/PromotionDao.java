package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.PromotionDaoSqls.SELECT_SAVE_FILE_NAME;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Promotion;

@Repository
public class PromotionDao {
	private NamedParameterJdbcTemplate jdbc;

	public PromotionDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Promotion> selectAll() {
		return jdbc.queryForList(SELECT_SAVE_FILE_NAME, Collections.EMPTY_MAP);
	}
}
