package com.nts.reservation.service.impl;

import static com.nts.reservation.property.Const.ALL_CATEGORIES;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ProductMapper;
import com.nts.reservation.dao.ProductPriceDao;
import com.nts.reservation.dao.ProductThumbnailDao;
import com.nts.reservation.dto.Category;
import com.nts.reservation.dto.PriceInfo;
import com.nts.reservation.dto.ProductPageInfo;
import com.nts.reservation.dto.ProductThumbnail;
import com.nts.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productDao;
	@Autowired
	private ProductThumbnailDao thumbnailInfoDao;
	@Autowired
	private ProductPriceDao priceDao;

	@Override
	@Transactional
	public int getProductCountByCategoryId(int categoryId) {
		return productDao.selectProductCount(categoryId);
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

	@Override
	@Transactional
	public ProductPageInfo getProductPageInfoByDisplayInfoId(long displayInfoId) {
		return productDao.selectDetailPageInfo(displayInfoId);
	}

	@Override
	@Transactional
	public List<PriceInfo> getPriceInfoByProductId(long productId) {
		return priceDao.selectPriceInfoByProductId(productId);
	}

	@Override
	@Transactional
	public List<String> getProductFileNameByProductIdAndType(long productId, String type) {
		List<String> saveFileNames = productDao.selectByDisplayInfoIdAndType(productId, type);
		return saveFileNames;
	}
}
