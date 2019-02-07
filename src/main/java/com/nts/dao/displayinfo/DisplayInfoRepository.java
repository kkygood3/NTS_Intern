package com.nts.dao.displayinfo;

import static com.nts.sqls.displayinfo.DisplayInfoSqls.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.dto.displayinfo.DisplayInfo;
import com.nts.dto.displayinfo.DisplayInfoImage;

@Repository
public class DisplayInfoRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private RowMapper<DisplayInfo> displayInfoRowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	private RowMapper<DisplayInfoImage> displayInfoImageRowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);

	/**
	 * @desc 전시 Id로 전시 조회
	 * @param displayInfoId
	 * @return displayInfo
	 */
	public DisplayInfo selectDisplayInfo(int displayInfoId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("displayInfoId", displayInfoId);

		return namedParameterJdbcTemplate.queryForObject(SELECT_DISPLAY_INFO, params, displayInfoRowMapper);
	}

	/**
	 * @desc 전시 Id로 Image 가져오기
	 * @param displayInfoId
	 * @return displayInfoImage
	 */
	public DisplayInfoImage selectDisplayInfoImage(int displayInfoId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("displayInfoId", displayInfoId);

		return namedParameterJdbcTemplate.queryForObject(SELECT_DISPLAY_INFO_IMAGE, params, displayInfoImageRowMapper);
	}
	
	/**
	 * @desc displayInfoId가 있는지 체크
	 * @param displayInfoId
	 */
	public boolean checkDisplayInfoIdIsNull(int displayInfoId) {

		Map<String,Object> params = new HashMap<String,Object>();
		params.put("displayInfoId", displayInfoId);
		
		return namedParameterJdbcTemplate.queryForObject(CHECK_DISPLAY_INFO_ID_IS_NULL, params, Boolean.class);
	}
}
