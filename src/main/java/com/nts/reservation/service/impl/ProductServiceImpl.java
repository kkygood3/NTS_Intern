package com.nts.reservation.service.impl;

import static com.nts.reservation.property.Const.ALL_CATEGORIES;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.dao.ProductThumbnailDao;
import com.nts.reservation.dto.Category;
import com.nts.reservation.dto.ProductThumbnail;
import com.nts.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductThumbnailDao thumbnailInfoDao;

	@Override
	@Transactional
	public int getProductCountByCategoryId(int categoryId) {
		if (categoryId == 0) {
			return productDao.selectCount();
		} else {
			return productDao.selectCount(categoryId);
		}
	}


	@Override
	@Transactional
	public List<ProductThumbnail> getProductThumbnailsByCategoryIdWithPaging(int categoryId, int start, int limit) {
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
