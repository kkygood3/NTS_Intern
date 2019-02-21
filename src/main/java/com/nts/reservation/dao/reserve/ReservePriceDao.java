/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.reserve;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.annotations.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dao.sql.ReserveSqls;
import com.nts.reservation.dto.reserve.ReservePrice;

/**
 * reserve 페이지 로드시 사용하는 상품 가격 정보
 */
public interface ReservePriceDao {
	/**
	 * reserve 페이지를 로드할 때 상품의 가격 정보를 조회
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 */
	public List<ReservePrice> selectReservePrice(
		@Param("displayInfoId") int displayInfoId);
}
