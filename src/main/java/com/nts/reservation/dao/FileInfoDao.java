package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.FileInfoDaoSqls.SELECT_SAVE_FILE_NAME_BY_DISPLAY_INFO_ID_AND_TYPE;
import static com.nts.reservation.property.Const.PRODUCT_ID;
import static com.nts.reservation.property.Const.TYPE;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

@Repository
public class FileInfoDao extends BaseDao {
	public FileInfoDao(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 해당상품의 파일이름을 셀렉트
	 * @return save file name
	 */
	public String selectSaveFileNameByDisplayInfoIdAndType(long productId, String type) {
		Map<String, Object> params = new HashMap<>();
		params.put(PRODUCT_ID, productId);
		params.put(TYPE, type);
		return getJDBC().queryForObject(SELECT_SAVE_FILE_NAME_BY_DISPLAY_INFO_ID_AND_TYPE, params, String.class);
	}
}
