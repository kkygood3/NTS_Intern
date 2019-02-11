package com.nts.reservation.category.dao;

import java.util.List;

import com.nts.reservation.category.dto.Category;

public interface CategoryDao {

	List<Category> selectCategories();
}
