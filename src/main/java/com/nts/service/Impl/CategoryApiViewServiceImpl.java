package com.nts.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.dao.CategoryApiViewDao;
import com.nts.dto.CategoryApiView;
import com.nts.service.CategoryApiViewService;

@Service
public class CategoryApiViewServiceImpl implements CategoryApiViewService {

	@Autowired
	CategoryApiViewDao categoryApiViewDao;

	@Override
	public List<CategoryApiView> getAllCategories() {
		return categoryApiViewDao.selectAllCategories();
	}
	
}
