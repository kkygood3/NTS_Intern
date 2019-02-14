package com.nts.reservation.dao;

import static com.nts.reservation.dao.sqls.ReservationDaoSqls.INSERT_RESERVATION_RESERVATION_INFO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.reservation.ReservationInput;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

@Repository
public class ReservationDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	/*ReservationInput [displayInfoId=11, 
	prices=[Price [count=3, productPriceId=28, reservationInfoId=null, reservationInfoPriceId=null],
	 Price [count=0, productPriceId=29, reservationInfoId=null, reservationInfoPriceId=null]],
	  reservationEmail=wodnjs6512@live.co.kr, 
	  reservationTelephone=010-4592-2156, 
	  reservationYearMonthDay=2019/2/14]*/

	public long insertReservationInfo(ReservationInput input) {
		//		Map<String, Object> params = new HashMap<>();
		//		params.put("productId", input.getProductId());
		//		params.put("displayInfoId", input.getDisplayInfoId());
		//		params.put("reservationName", input.getReservationName());
		//		params.put("reservationTel", input.getReservationTelephone());
		//		params.put("reservationEmail", input.getReservationEmail());
		//		params.put("reservationYearMonthDay", input.getReservationYearMonthDay());
		//		return jdbc.update(INSERT_RESERVATION_RESERVATION_INFO, params);
		KeyHolder holder = new GeneratedKeyHolder();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("productId", input.getProductId());
		params.addValue("displayInfoId", input.getDisplayInfoId());
		params.addValue("reservationName", input.getReservationName());
		params.addValue("reservationTel", input.getReservationTelephone());
		params.addValue("reservationEmail", input.getReservationEmail());
		params.addValue("reservationYearMonthDay", input.getReservationYearMonthDay());
		jdbc.update(INSERT_RESERVATION_RESERVATION_INFO, params, holder, new String[] {"id"});
		return holder.getKey().intValue();
	}
}
