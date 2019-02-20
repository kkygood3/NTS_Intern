package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.DatailPageInfoDaoSqls.SELECT_BY_DISPLAY_INFO_ID;
import static com.nts.reservation.property.Const.DISPLAY_INFO_ID;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.ProductPageInfo;

/**
 * 상품상세화면 관련 정보 접근하는 클래스 
 * @author si yoon
 *
 */
@Repository
public class DatailPageInfoDao extends BaseDao {
	RowMapper<ProductPageInfo> rowMapper = BeanPropertyRowMapper.newInstance(ProductPageInfo.class);

	DatailPageInfoDao(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * displayInfoId에 따라 상품상세정보 셀렉트한다
	 * @param displayInfoId 조화할 id
	 * @return 쿼리실행결과 상세페이지 정보
	 */
	public ProductPageInfo selectDetailPageInfo(long displayInfoId) {
		Map<String, Long> params = new HashMap<>();
		params.put(DISPLAY_INFO_ID, displayInfoId);
		return getJdbc().queryForObject(SELECT_BY_DISPLAY_INFO_ID, params, rowMapper);
	}
}
