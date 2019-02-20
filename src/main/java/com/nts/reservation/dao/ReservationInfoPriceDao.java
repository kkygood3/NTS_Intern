package com.nts.reservation.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.ReservationInfoPrice;

/**
 * 상품 가격관련 DB접근 클래스
 * @author si yoon
 *
 */
@Repository
public class ReservationInfoPriceDao extends BaseDao {
	private SimpleJdbcInsert insertAction;
	
	ReservationInfoPriceDao(DataSource dataSource) {
		super(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_info_price").usingGeneratedKeyColumns("id");
	}
	
	// TODO: 한번에 여러줄 insert할수있는지 찾아보기
	/**
	 * 예약 가격정보 insert
	 * @param price 가격정보
	 * @return 예약 가격 id
	 */
	public long insertReservationInfoPrice(ReservationInfoPrice price) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(price);
		long id = insertAction.executeAndReturnKey(params).longValue();
		return id;
	}
}
