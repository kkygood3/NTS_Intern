package com.nts.reservation.display.service.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.nts.reservation.product.dto.ImageType;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;

@Service
public class DisplayServiceImpl implements DisplayService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CommentService commentService;
	@Autowired
	DisplayDao displayDao;
	@Autowired
	ProductDao productDao;

	@Override
	public DisplayResponse getDisplayInfo(int displayInfoId, int limit) {
		DisplayInfo displayInfo = displayDao.selectDisplayInfo(displayInfoId);

		if (displayInfo == null) {
			logger.warn("{} - displayInfoId : {} / Does not exist displayInfo", this.getClass(), displayInfo);
			throw new IllegalArgumentException("Bad Request! Parameter (displayInfo)");
		}

		DisplayInfoImage displayInfoImage = displayDao.selectDisplayInfoImage(displayInfoId);
		List<ProductImage> productImages = productDao.selectProductImages(displayInfoId, ImageType.ma);
		List<ProductPrice> productPrices = productDao.selectProductPrices(displayInfoId);
		CommentResponse comments = commentService.getComments(displayInfoId, limit);
		
		return DisplayResponse.builder()
			.displayInfo(displayInfo)
			.commentResponse(comments)
			.displayInfoImage(displayInfoImage)
			.productImages(productImages)
			.productPrices(productPrices).build();
	}
}
