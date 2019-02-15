package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nts.reservation.dto.DatailPageInfo;
import com.nts.reservation.dto.ReviewPageInfo;
import com.nts.reservation.service.CommentService;
import com.nts.reservation.service.DetailPageInfoService;

/**
 * 디테일 페이지 url 맵핑
 * @author si yoon
 *
 */
@Controller
@RequestMapping(path = "/detail")
public class ProductController {
	@Autowired
	private DetailPageInfoService detailPageInfoService;

	/**
	 * 디테일 페이지에 표시할 정보 담아서 url맵핑한다
	 * @param displayInfoId 조회할 상품 정보
	 * @param model 표시할 정보를 담는다
	 * @return 뷰이름 리턴
	 */
	@GetMapping(path = "/{displayInfoId}")
	public String detail(@PathVariable(name = "displayInfoId", required = true) long displayInfoId,
		ModelMap model) {
		DatailPageInfo datailPageInfo = detailPageInfoService.getDetailPageInfo(displayInfoId);

		model.addAttribute("displayInfoId", displayInfoId);
		model.addAttribute("pageInfo", datailPageInfo);

		return "detail";
	}
	
	@Autowired
	private CommentService commentService;

	/**
	 * 리뷰페이지에 표시할 정보 담아서 url맵핑한다
	 * @return 리뷰페이지
	 */
	@GetMapping(path = "/{displayInfoId}/review")
	public String review(@PathVariable(name = "displayInfoId", required = true) long displayInfoId,
		ModelMap model) {
		ReviewPageInfo reviewPageInfo = commentService.getReviewPageInfo(displayInfoId);
		model.addAttribute("pageInfo", reviewPageInfo);
		model.addAttribute("displayInfoId", displayInfoId);

		return "review";
	}
}
