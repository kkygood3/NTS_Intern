/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.imagefile.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.imagefile.dao.ImageFileDao;
import com.nts.reservation.imagefile.dto.ImageFile;
import com.nts.reservation.imagefile.service.ImageFileService;

@Service
public class ImageFileServiceImpl implements ImageFileService{
	
	@Autowired
	ImageFileDao imageFileDao;
	
	@Override
	public List<ImageFile> getImageFile(int fileId) {
		return imageFileDao.selectImageFile(fileId);
	}
}
