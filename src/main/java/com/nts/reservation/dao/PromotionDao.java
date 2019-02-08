package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.PromotionDaoSqls.SELECT_MA_TYPE_SAVE_FILE_NAMES_IN_PROMOTION;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Promotion;

/**
 * 프로모션 관련 정보에 접근하는 클래스
 * @author si yoon
 *
 */
@Repository
public class PromotionDao extends BaseDao {
	public PromotionDao(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 전체 프로모션 이미지 셀렉트하는 메소드
	 * @return 전체 프로모션 이미지 이름
	 */
	public List<Promotion> selectMaTypeFileNames() {
		return getJDBC().queryForList(SELECT_MA_TYPE_SAVE_FILE_NAMES_IN_PROMOTION, Collections.EMPTY_MAP);
	}
}
