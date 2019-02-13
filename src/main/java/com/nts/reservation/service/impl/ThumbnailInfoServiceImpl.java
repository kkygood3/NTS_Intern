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

/**
 * 메인페이지 썸네일 관련 서비스 로직 수행하는 클래스
 * @author si yoon
 *
 */
@Service
public class ThumbnailInfoServiceImpl implements ThumbnailInfoService {
	@Autowired
	private ThumbnailInfoDao thumbnailInfoDao;

	/**
	 * 해당카테고리 (0이면 전체)에서 start부터 limit개 썸네일정보 리턴 
	 */
	@Override
	@Transactional
	public List<ThumbnailInfo> getThumbnailInfos(int start, int categoryId, int limit) {
		List<ThumbnailInfo> thumbnailInfoList;
		if (categoryId == ALL_CATEGORIES) {
			thumbnailInfoList = thumbnailInfoDao.selectFromAllCategorisWithPaging(start, limit);
			return thumbnailInfoList;
		} else {
			Category c = new Category();
			c.setId(categoryId);
			thumbnailInfoList = thumbnailInfoDao.selectFromTheCategoryWithPaging(c, start, limit);
		}
		return thumbnailInfoList;
	}
}
