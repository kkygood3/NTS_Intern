package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.container.DetailDisplay;
import com.nts.reservation.dao.detail.CommentDao;
import com.nts.reservation.dao.detail.CommentImageDao;
import com.nts.reservation.dao.detail.DisplayInfoDao;
import com.nts.reservation.dao.detail.DisplayInfoImageDao;
import com.nts.reservation.dao.detail.ProductImageDao;
import com.nts.reservation.dao.detail.ProductPriceDao;
import com.nts.reservation.dto.detail.Comment;
import com.nts.reservation.dto.detail.DisplayInfo;
import com.nts.reservation.dto.detail.DisplayInfoImage;
import com.nts.reservation.dto.detail.ProductImage;
import com.nts.reservation.dto.detail.ProductPrice;
import com.nts.reservation.service.DetailDisplayService;

@Service
public class DetailDisplayServiceImpl implements DetailDisplayService {

	@Autowired
	private CommentDao commentDao;
	@Autowired
	private CommentImageDao commentImageDao;
	@Autowired
	private DisplayInfoDao displayInfoDao;
	@Autowired
	private DisplayInfoImageDao displayInfoImageDao;
	@Autowired
	private ProductImageDao productImageDao;
	@Autowired
	private ProductPriceDao productPriceDao;

	@Override
	public DetailDisplay getDetailDisplay(int displayInfoId) {
		DisplayInfo displayInfo = displayInfoDao.selectDisplayInfoById(displayInfoId);
		DisplayInfoImage displayInfoImage = displayInfoImageDao.selectDisplayInfoImageById(displayInfoId);

		int productId = displayInfo.getProductId();
		List<ProductImage> productImages = productImageDao.selectProductImageByProductId(productId);
		List<ProductPrice> productPrices = productPriceDao.selectProductPriceByProductId(productId);

		
		List<Comment> comments = commentDao.selectCommentByProductId(productId);
		for(Comment comment : comments) {
			int commentId = comment.getCommentId();
			comment.setCommentImage(commentImageDao.selectCommentImageByCommentId(commentId));
		}
		
		return new DetailDisplay(commentDao.selectAvgScoreByProductId(productId), comments, displayInfo, displayInfoImage, productImages, productPrices);
	}

}
