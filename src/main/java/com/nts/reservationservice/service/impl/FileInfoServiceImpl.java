/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservationservice.dao.FileInfoDao;
import com.nts.reservationservice.dto.FileInfoDto;
import com.nts.reservationservice.service.FileInfoService;

@Service
public class FileInfoServiceImpl implements FileInfoService {

	@Autowired
	FileInfoDao fileInfoDao;

	@Override
	@Transactional
	public FileInfoDto getFileInfoById(Long id) {
		return fileInfoDao.selectFileInfoById(id);
	}

}
