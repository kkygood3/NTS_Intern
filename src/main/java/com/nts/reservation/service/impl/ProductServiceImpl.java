package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.ProductMapper;
import com.nts.reservation.dto.PriceInfo;
import com.nts.reservation.dto.ProductPageInfo;
import com.nts.reservation.dto.ProductDisplayItem;
import com.nts.reservation.dto.CommentWritePageInfo;
import com.nts.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productDao;

	@Override
	public int getProductCountByCategoryId(int categoryId) {
		return productDao.selectProductCount(categoryId);
	}

	@Override
	public List<ProductDisplayItem> getProductThumbnailsByCategoryIdWithPaging(int categoryId, int start, int limit) {
		return productDao.selectProductThumbnailByCategoryIdWithPaging(categoryId, start, limit);
	}

	@Override
	public ProductPageInfo getProductPageInfoByDisplayInfoId(long displayInfoId) {
		return productDao.selectDetailPageInfo(displayInfoId);
	}

	@Override
	public List<PriceInfo> getPriceInfoByProductId(long productId) {
		return productDao.selectPriceInfoByProductId(productId);
	}

	@Override
	public List<String> getProductFileNameByProductIdAndType(long productId, String type) {
		List<String> saveFileNames = productDao.selectByDisplayInfoIdAndType(productId, type);
		return saveFileNames;
	}

	@Override
	public CommentWritePageInfo getProductInfoByReservationInfoId(long reservationInfoId) {
		return productDao.selectProductInfoByReservationInfoId(reservationInfoId);
	}
}
