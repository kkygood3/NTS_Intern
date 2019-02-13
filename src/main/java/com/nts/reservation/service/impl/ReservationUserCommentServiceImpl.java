package com.nts.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ReservationUserCommentDao;
import com.nts.reservation.service.ReservationUserCommentService;

@Service
public class ReservationUserCommentServiceImpl implements ReservationUserCommentService {
	@Autowired
	private ReservationUserCommentDao reservationUserCommentDao;

	@Override
	@Transactional
	public double getAverageScore(long productId) {
		return reservationUserCommentDao.selectAverageScore(productId);
	}

	@Override
	@Transactional
	public int getCount(long productId) {
		return reservationUserCommentDao.selectCount(productId);
	}
}
