package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.PriceInfoDaoSqls.*;
import static com.nts.reservation.property.Const.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.PriceInfo;

/**
 * 상품가격 테이블에 접근하는 클래스
 * @author si yoon
 *
 */
@Repository
public class ProductPriceDao extends BaseDao {
	RowMapper<PriceInfo> rowMapper = BeanPropertyRowMapper.newInstance(PriceInfo.class);

	ProductPriceDao(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 
	 * 예약페이지에 표시할 가격정보
	 * @param productId 상품 아이디
	 * @return 가격정도
	 */
	public List<PriceInfo> selectPriceInfoByProductId(long productId) {
		Map<String, Long> params = new HashMap<>();
		params.put(PRODUCT_ID, productId);
		return getJdbc().query(SELECT_PRICE_INFO_BY_PRODUCT_ID, params, rowMapper);
	}
}
