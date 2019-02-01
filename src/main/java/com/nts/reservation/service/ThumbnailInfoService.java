package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.ThumbnailInfo;

public interface ThumbnailInfoService {
	public List<ThumbnailInfo> getThumbnailInfos(int start, int categoryId, int limit);
}
