package com.nts.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.FileInfoDao;
import com.nts.reservation.service.FileInfoService;

@Service
public class FileInfoServiceImpl implements FileInfoService {
	@Autowired
	FileInfoDao fileInfoDao;
	
	@Override
	@Transactional
	public String getSaveFileNames(long productId, String type) {
		String saveFileName = fileInfoDao.selectSaveFileNameByDisplayInfoIdAndType(productId, type);
		return saveFileName;
	}
}
