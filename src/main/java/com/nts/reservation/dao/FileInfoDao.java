package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.FileInfoDaoSqls.SELECT_BY_PRODUCT_ID_AND_TYPE;
import static com.nts.reservation.property.Const.PRODUCT_ID;
import static com.nts.reservation.property.Const.TYPE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.FileInfo;

@Repository
public class FileInfoDao extends BaseDao {
	RowMapper<FileInfo> rowMapper = BeanPropertyRowMapper.newInstance(FileInfo.class);
	public FileInfoDao(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 해당상품의 파일이름을 셀렉트
	 * @return save file name
	 */
	public List<FileInfo> selectByDisplayInfoIdAndType(long productId, String type) {
		Map<String, Object> params = new HashMap<>();
		params.put(PRODUCT_ID, productId);
		params.put(TYPE, type);

		return getJdbc().query(SELECT_BY_PRODUCT_ID_AND_TYPE, params, rowMapper);
	}
}
