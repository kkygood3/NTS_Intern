package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.FileInfoDaoSqls.SELECT_BY_PRODUCT_ID_AND_TYPE;
import static com.nts.reservation.property.Const.PRODUCT_ID;
import static com.nts.reservation.property.Const.TYPE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

/**
 * 파일 관련 정보 접근하는 클래스
 * @author si yoon
 *
 */
@Repository
public class FileInfoDao extends BaseDao {
	public FileInfoDao(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 상품이랑 타입에 따라 파일이름 가져오는 메소드
	 * @param productId 조회할 상품 id
	 * @param type 조회할 타입  ma(main), et(etc), th(thumbnail)
	 * @return 쿼리 실행결과 파일이름 리스트
	 */
	public List<String> selectByDisplayInfoIdAndType(long productId, String type) {
		Map<String, Object> params = new HashMap<>();
		params.put(PRODUCT_ID, productId);
		params.put(TYPE, type);

		return getJdbc().queryForList(SELECT_BY_PRODUCT_ID_AND_TYPE, params, String.class);
	}
}
