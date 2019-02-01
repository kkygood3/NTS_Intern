package com.nts.reservation.service.impl;

import static com.nts.reservation.property.Const.ALL_CATEGORIES;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ThumbnailInfoDao;
import com.nts.reservation.dto.Category;
import com.nts.reservation.dto.ThumbnailInfo;
import com.nts.reservation.service.ThumbnailInfoService;

/*
 * 메인페이지 썸네일 4개씩 리턴
 * @author 시윤
 */
@Service
public class ThumbnailInfoServiceImpl implements ThumbnailInfoService {
	@Autowired
	private ThumbnailInfoDao thumbnailInfoDao;

	@Override
	@Transactional
	public List<ThumbnailInfo> getThumbnailInfos(int start, int categoryId, int limit) {
		List<ThumbnailInfo> thumbnailInfoList;
		if (categoryId == ALL_CATEGORIES) {
			thumbnailInfoList = thumbnailInfoDao.selectAllCategoris(start, limit);
			return thumbnailInfoList;
		} else {
			Category c = new Category();
			c.setId(categoryId);
			thumbnailInfoList = thumbnailInfoDao.selectByCategory(c, start, limit);
		}
		return thumbnailInfoList;
	}

}
