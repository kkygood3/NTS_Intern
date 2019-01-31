package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ThumbnailInfoDao;
import com.nts.reservation.dto.Category;
import com.nts.reservation.dto.ThumbnailInfo;
import com.nts.reservation.service.ThumbnailInfoService;

@Service
public class ThumbnailInfoServiceImpl implements ThumbnailInfoService {
	@Autowired
	ThumbnailInfoDao thumbnailInfoDao;

	@Override
	@Transactional // 내부적으로 readonly로 처리
	public List<ThumbnailInfo> getThumbnailInfos(int start) {
		List<ThumbnailInfo> thumbnailInfoList = thumbnailInfoDao.selectAllCategoris(start, LIMIT);
		return thumbnailInfoList;
	}

	@Override
	public List<ThumbnailInfo> getThumbnailInfosByCategory(int start, int categoryId) {
		Category c = new Category();
		c.setId(categoryId);
		List<ThumbnailInfo> thumbnailInfoList = thumbnailInfoDao.selectByCategory(c, start, LIMIT);
		return thumbnailInfoList;
	}
}
