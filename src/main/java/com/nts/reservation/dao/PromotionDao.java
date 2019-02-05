package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.PromotionDaoSqls.SELECT_SAVE_FILE_NAMES_IN_PROMOTION;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Promotion;

/*
 * 프로모션 이미지 이름 셀렉트
 * @author 시윤
 */
@Repository
public class PromotionDao {
	private NamedParameterJdbcTemplate jdbc;

	public PromotionDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Promotion> selectAll() {
		return jdbc.queryForList(SELECT_SAVE_FILE_NAMES_IN_PROMOTION, Collections.EMPTY_MAP);
	}
}
