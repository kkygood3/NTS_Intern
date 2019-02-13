package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.CategoryDao;
import com.nts.reservation.dto.Category;
import com.nts.reservation.service.CategoryService;

/**
 * 카테고리 관련 서비스 로직 수행하는 클래스
 * @author si yoon
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao promotionDao;

	/**
	 * 전체 카테고리 셀렉트
	 */
	@Override
	@Transactional
	public List<Category> getCategoris() {
		List<Category> list = promotionDao.selectAll();
		return list;
	}
}
