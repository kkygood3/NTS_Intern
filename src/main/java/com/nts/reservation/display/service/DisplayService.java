package com.nts.reservation.display.service;

import com.nts.reservation.display.dto.DisplayInfo;
import com.nts.reservation.display.dto.DisplayInfoImage;

public interface DisplayService {

	DisplayInfo getDisplayInfo(int displayInfoId);

	DisplayInfoImage getDisplayInfoImage(int displayInfoId);
}
