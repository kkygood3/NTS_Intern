package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nts.reservation.dto.CommentPageInfo;
import com.nts.reservation.dto.ProductPageInfo;
import com.nts.reservation.dto.ReservationPageInfo;
import com.nts.reservation.service.CommentService;
import com.nts.reservation.service.ProductService;
import com.nts.reservation.service.ReservationService;

/**
 * 상품관련 페이지 url 맵핑
 * @author si yoon
 *
 */
@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ReservationService reservationService;

	/**
	 * 디테일 페이지에 표시할 정보 담아서 url맵핑한다
	 * @param displayInfoId 조회할 상품 정보
	 * @param model 표시할 정보를 담는다
	 * @return 뷰이름 리턴
	 */
	@GetMapping("/{productId}/display/{displayInfoId}")
	public String detail(ModelMap model,
			@PathVariable(name = "displayInfoId", required = true) long displayInfoId) {
		ProductPageInfo datailPageInfo = productService.getProductPageInfoByDisplayInfoId(displayInfoId);

		model.addAttribute("displayInfoId", displayInfoId);
		model.addAttribute("pageInfo", datailPageInfo);

		return "detail";
	}

	/**
	 * 리뷰페이지에 표시할 정보 담아서 url맵핑한다
	 * @param displayInfoId 조회할 상품
	 * @param model 표시할 정보를 담는다
	 * @return 뷰이름 리턴
	 */
	@GetMapping("/{productId}/comment")
	public String review(ModelMap model,
			@PathVariable(name = "productId", required = true) long productId) {
		CommentPageInfo reviewPageInfo = commentService.getCommentPageInfoByProductId(productId);
		model.addAttribute("pageInfo", reviewPageInfo);
		model.addAttribute("displayInfoId", productId);

		return "review";
	}

	/**
	 * 예약페이지에 표시할 정보 담아서 url맵핑한다
	 * @param displayInfoId 조회할 상품
	 * @param model 표시할 정보를 담는다
	 * @return 뷰이름 리턴
	 */
	@GetMapping("/{productId}/display/{displayInfoId}/reservation")
	public String getReservation(ModelMap model,
			@PathVariable(name = "displayInfoId", required = true) long displayInfoId) {
		ReservationPageInfo reservationPageInfo = reservationService.getReservationPageInfoByDisplayInfoId(displayInfoId);
		model.addAttribute("pageInfo", reservationPageInfo);
		return "reservation";
	}
}
