package com.nts.reservation.service.impl;

import java.util.List;

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
	public List<String> getProductFileNameByProductIdAndType(long productId, String type) {
		List<String> saveFileNames = fileInfoDao.selectByDisplayInfoIdAndType(productId, type);
		return saveFileNames;
	}
}
