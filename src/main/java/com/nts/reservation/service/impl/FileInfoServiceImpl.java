package com.nts.reservation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.FileInfoDao;
import com.nts.reservation.dto.FileInfo;
import com.nts.reservation.service.FileInfoService;

@Service
public class FileInfoServiceImpl implements FileInfoService {
	@Autowired
	FileInfoDao fileInfoDao;
	
	@Override
	@Transactional
	public List<String> getSaveFileName(long productId, String type) {
		List<FileInfo> fileInfos = fileInfoDao.selectByDisplayInfoIdAndType(productId, type);
		List <String> saveFileNames = new ArrayList<String>();
		for (FileInfo fileInfo : fileInfos) {
			saveFileNames.add(fileInfo.getSaveFileName());
		}
		return saveFileNames;
	}
}
