package com.nts.reservation.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.Category;
import com.nts.reservation.service.CategoryService;

/**
 * 카테고리 관련 API 클래스
 * @author 시윤
 *
 */
@RestController
@RequestMapping("/category")
public class CategoryApiController {
	@Autowired
	private CategoryService categoryService;

	/**
	 * 전체 카테고리를 리턴하는 메소드
	 * @return 전체 카테고리 목록
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Category> getCategoris() {
		return categoryService.getCategoris();
	}
}
