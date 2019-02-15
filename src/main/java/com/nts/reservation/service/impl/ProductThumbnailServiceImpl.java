package com.nts.reservation.service.impl;

import static com.nts.reservation.property.Const.ALL_CATEGORIES;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ProductThumbnailDao;
import com.nts.reservation.dto.Category;
import com.nts.reservation.dto.ProductThumbnail;
import com.nts.reservation.service.ProductThumbnailService;

@Service
public class ProductThumbnailServiceImpl implements ProductThumbnailService {
	@Autowired
	private ProductThumbnailDao thumbnailInfoDao;

	@Override
	@Transactional
	public List<ProductThumbnail> getThumbnailInfos(int categoryId, int start, int limit) {
		List<ProductThumbnail> thumbnailInfoList;
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
