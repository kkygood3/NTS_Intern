package com.nts.reservation.service.impl.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.review.ReviewResponseDao;
import com.nts.reservation.dto.review.ReviewResponse;
import com.nts.reservation.service.review.ReviewResponseService;

@Service
public class ReviewResponseServiceImpl implements ReviewResponseService {
	@Autowired
	ReviewResponseDao reviewResponseDao;

	@Override
	public List<ReviewResponse> getReviewResponse(int displayInfoId, int pagingLimit) {
		return reviewResponseDao.selectDetailPageById(displayInfoId, pagingLimit);
	}

}
