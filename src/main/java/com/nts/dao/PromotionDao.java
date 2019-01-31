/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.dto.Promotion;
import static com.nts.dao.PromotionDaoSqls.*;


/**
 *
 * @description : PromotionDao
 * @package : com.nts.dao
 * @filename : PromotionDao.java
 * @author : 최석현
 * @method : PromotionDao(DataSource dataSource)
 * @method : List<Promotion> selectAll()
 * 
 */
@Repository
public class PromotionDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);
	
	public PromotionDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Promotion> selectPromotionsAll(){
		return jdbc.query(SELECT_PROMOTIONS_ALL, rowMapper);
	}
}