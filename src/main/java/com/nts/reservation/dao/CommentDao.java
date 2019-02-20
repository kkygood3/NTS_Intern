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

/**
 * 코멘드 화면 관련정보 처리하는 클래스
 * @author si yoon
 *
 */
@Repository
public class CommentDao extends BaseDao {
	private RowMapper<CommentDisplayInfo> rowMapper = BeanPropertyRowMapper.newInstance(CommentDisplayInfo.class);

	public CommentDao(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 전체 코멘트 화면 정보 페이징해서 셀렉트하는 메소드
	 * @param productId 해당 상품 id
	 * @param start 시작 인덱스
	 * @param limit 페이징 사이즈
	 * @return 쿼리 실행 결과 코멘트 리스트
	 */
	public List<CommentDisplayInfo> selectFromTheProductWithPageing(long productId, Integer start, Integer limit) {
		Map<String, Object> params = new HashMap<>();
		params.put(PRODUCT_ID, productId);
		params.put(START, start);
		params.put(LIMIT, limit);
		return getJdbc().query(SELECT_COMMENT_WITH_PAGING, params, rowMapper);
	}
}
