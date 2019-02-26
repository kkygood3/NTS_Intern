/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.imagefile.dao.daoimpl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.imagefile.dao.ImageFileDao;
import com.nts.reservation.imagefile.dao.sqls.ImageFileDaoSqls;
import com.nts.reservation.imagefile.dto.ImageFile;

@Repository
public class ImageFileDaoImpl implements ImageFileDao{
	private NamedParameterJdbcTemplate jdbc;

	public ImageFileDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	private RowMapper<ImageFile> imageFileRowMapper = BeanPropertyRowMapper.newInstance(ImageFile.class);

	@Override
	public List<ImageFile> selectImageFile(int fileId) {
		Map<String, Integer> param = Collections.singletonMap("fileId", fileId);
		return jdbc.query(ImageFileDaoSqls.SELECT_IMAGE_FILE, param, imageFileRowMapper);
	}
}
