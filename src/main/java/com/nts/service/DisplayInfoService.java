package com.nts.service;


import com.nts.dto.displayinfodto.DisplayInfo;
import com.nts.dto.displayinfodto.DisplayInfoImage;

public interface DisplayInfoService {

	public DisplayInfo getDisplayInfoByDisplayInfoId(int displayInfoId);

	public DisplayInfoImage getDisplayInfoImageByDisplayInfoId(int displayInfoId);
}