package com.nts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.dao.displayinfodao.DisplayInfoDao;
import com.nts.dto.displayinfodto.DisplayInfo;
import com.nts.dto.displayinfodto.DisplayInfoImage;
import com.nts.exception.ValidationException;
import com.nts.service.DisplayInfoService;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {
	@Autowired
	private DisplayInfoDao displayInfoDao;

	@Override
	@Transactional(readOnly = true)
	public DisplayInfo getDisplayInfoByDisplayInfoId(int displayInfoId) throws ValidationException {
		if (displayInfoId < 0) {
			throw new ValidationException("displayInfoId : " + displayInfoId);
		}
		return displayInfoDao.selectDisplayInfoByDisplayInfoId(displayInfoId);
	}

	@Override
	@Transactional(readOnly = true)
	public DisplayInfoImage getDisplayInfoImageByDisplayInfoId(int displayInfoId) throws ValidationException {
		if (displayInfoId < 0) {
			throw new ValidationException("displayInfoId : " + displayInfoId);
		}
		return displayInfoDao.selectDisplayInfoImageByDisplayInfoId(displayInfoId);
	}

}
