/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.FileDao;
import com.nts.reservation.dto.FileInfo;
import com.nts.reservation.service.FileIoService;

/**
* @author  : 이승수
*/
@Service
public class FileIoServiceImpl implements FileIoService {
	@Autowired
	private FileDao fileDao;

	@Override
	public FileInfo getFileInfoByProductId(Integer fileId) {
		return fileDao.selectFileInfo(fileId);
	}

	@Override
	public int getFileIdByProductId(Integer productId) {
		return fileDao.selectFileIdByProductId(productId);
	}

	@Override
	public int getFileIdByDisplayInfoId(Integer displayInfoId) {
		return fileDao.selectFileIdByDisplayInfoId(displayInfoId);
	}

	@Override
	public int setFileInfo(FileInfo fileInfo) {
		return fileDao.insertFileInfo(fileInfo);
	}
}
