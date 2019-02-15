package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nts.reservation.dto.ReviewPageInfo;
import com.nts.reservation.service.CommentService;

/**
 * 리뷰 페이지 url 팹핑
 * @author si yoon
 *
 */
@Controller
public class ReviewController {
	@Autowired
	private CommentService commentService;

	/**
	 * 리뷰페이지에 표시할 정보 담아서 url맵핑한다
	 * @return 리뷰페이지
	 */
	@GetMapping(path = "detail/{displayInfoId}/review")
	public String review(@PathVariable(name = "displayInfoId", required = true) long displayInfoId,
		ModelMap model) {
		ReviewPageInfo reviewPageInfo = commentService.getReviewPageInfo(displayInfoId);
		model.addAttribute("pageInfo", reviewPageInfo);
		model.addAttribute("displayInfoId", displayInfoId);

		return "review";
	}
}
