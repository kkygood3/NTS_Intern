package com.nts.reservation.dao;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

@Repository
public class ReservationDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

}
