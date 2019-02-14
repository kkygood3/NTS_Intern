package com.nts.reservation.display.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.comment.dto.CommentResponse;
import com.nts.reservation.comment.service.CommentService;
import com.nts.reservation.display.dao.DisplayDao;
import com.nts.reservation.display.dto.DisplayInfo;
import com.nts.reservation.display.dto.DisplayInfoImage;
import com.nts.reservation.display.dto.DisplayResponse;
import com.nts.reservation.display.service.DisplayService;
import com.nts.reservation.product.dao.ProductDao;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;

@Service
public class DisplayServiceImpl implements DisplayService {

	@Autowired
	CommentService commentService;
	@Autowired
	DisplayDao displayDao;
	@Autowired
	ProductDao productDao;

	@Override
	public DisplayResponse getDisplayInfo(int displayInfoId) {
		DisplayInfo displayInfo = displayDao.selectDisplayInfo(displayInfoId);

		if (displayInfo == null) {
			throw new IllegalArgumentException();
		}

		CommentResponse comments = commentService.getComments(displayInfoId, 0);
		DisplayInfoImage displayInfoImage = displayDao.selectDisplayInfoImage(displayInfoId);
		List<ProductImage> productImages = productDao.selectProductImages(displayInfoId, "ma");
		List<ProductPrice> productPrices = productDao.selectProductPrices(displayInfoId);

		return DisplayResponse.builder()
			.comments(comments)
			.displayInfo(displayInfo)
			.displayInfoImage(displayInfoImage)
			.productImages(productImages)
			.productPrices(productPrices).build();
	}
}
