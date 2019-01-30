package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.ThumbnailInfo;

public interface ThumbnailInfoService {
	public static final Integer LIMIT = 4;
	public List<ThumbnailInfo> getThumbnailInfos(int start);
	public List<ThumbnailInfo> getThumbnailInfosByCategory(int start, int categoryId);
}
