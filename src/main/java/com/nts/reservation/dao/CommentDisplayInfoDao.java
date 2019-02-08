package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.CommentDisplayInfoDaoSqls.SELECT_COMMENT_WITH_PAGING;
import static com.nts.reservation.property.Const.LIMIT;
import static com.nts.reservation.property.Const.PRODUCT_ID;
import static com.nts.reservation.property.Const.START;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.CommentDisplayInfo;
import com.nts.reservation.dto.Product;

@Repository
public class CommentDisplayInfoDao extends BaseDao {
	private RowMapper<CommentDisplayInfo> rowMapper = BeanPropertyRowMapper.newInstance(CommentDisplayInfo.class);
	
	public CommentDisplayInfoDao(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 전체 프로모션 이미지 셀렉트하는 메소드
	 * @return 전체 프로모션 이미지 이름
	 */
	public List<CommentDisplayInfo> selectFromTheProductWithPageing(Product product, Integer start, Integer limit) {
		Map<String, Object> params = new HashMap<>();
		params.put(PRODUCT_ID, product.getId());
		params.put(START, start);
		params.put(LIMIT, limit);
		return getJDBC().query(SELECT_COMMENT_WITH_PAGING, params, rowMapper);
	}
}
