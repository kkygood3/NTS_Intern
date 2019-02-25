/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.dao.displayinfo;

import static com.nts.sqls.displayinfo.DisplayInfoSqls.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.dto.displayinfo.DisplayInfo;
import com.nts.dto.displayinfo.DisplayInfoImage;
import com.nts.exception.DisplayInfoNullException;

/**
 * @author 전연빈
 */
@Repository
public class DisplayInfoRepository {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private RowMapper<DisplayInfo> displayInfoRowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	private RowMapper<DisplayInfoImage> displayInfoImageRowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);

	public DisplayInfoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	/**
	 * @desc 전시 Id로 전시 조회
	 * @param displayInfoId
	 * @return displayInfo
	 * @throws DisplayInfoNullException 
	 */
	public DisplayInfo selectDisplayInfoByDisplayInfoId(int displayInfoId) throws DisplayInfoNullException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("displayInfoId", displayInfoId);

		try {
			return namedParameterJdbcTemplate.queryForObject(SELECT_DISPLAY_INFO, params, displayInfoRowMapper);
		} catch(EmptyResultDataAccessException e) {
			throw new DisplayInfoNullException("displayInfoId = "+displayInfoId);
		}

	}

	/**
	 * @desc 전시 Id로 Image 가져오기
	 * @param displayInfoId
	 * @return displayInfoImage
	 */
	public DisplayInfoImage selectDisplayInfoImageByDisplayInfoId(int displayInfoId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("displayInfoId", displayInfoId);

		return namedParameterJdbcTemplate.queryForObject(SELECT_DISPLAY_INFO_IMAGE, params, displayInfoImageRowMapper);
	}
	
}
