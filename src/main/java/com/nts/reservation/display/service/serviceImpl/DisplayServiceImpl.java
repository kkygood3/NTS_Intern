package com.nts.reservation.display.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.display.dao.DisplayDao;
import com.nts.reservation.display.dto.DisplayInfo;
import com.nts.reservation.display.dto.DisplayInfoImage;
import com.nts.reservation.display.service.DisplayService;

@Service
public class DisplayServiceImpl implements DisplayService {

	@Autowired
	DisplayDao displayDao;

	@Override
	public DisplayInfo getDisplayInfo(int displayInfoId) {
		return displayDao.selectDisplayInfo(displayInfoId);
	}

	@Override
	public DisplayInfoImage getDisplayInfoImage(int displayInfoId) {
		return displayDao.selectDisplayInfoImage(displayInfoId);
	}
}
