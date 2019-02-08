package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.CommentDisplayInfoDao;
import com.nts.reservation.dto.CommentDisplayInfo;
import com.nts.reservation.dto.Product;
import com.nts.reservation.service.CommentDisplayInfoService;

@Service
public class CommentDisplayInfoServiceImpl implements CommentDisplayInfoService {
	@Autowired
	private CommentDisplayInfoDao commentDisplayInfoDao;
	
	@Override
	@Transactional
	public List<CommentDisplayInfo> getCommentDisplayInfos(long productId, int start, int limit) {
		List<CommentDisplayInfo> commentDisplayInfoList;
		Product p = new Product();
		p.setId(productId);
		commentDisplayInfoList = commentDisplayInfoDao.selectFromTheProductWithPageing(p, start, limit);

		return commentDisplayInfoList;
	}
}
