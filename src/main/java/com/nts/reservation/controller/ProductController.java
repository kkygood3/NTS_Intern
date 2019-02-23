package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping(path = "/detail")
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
	@GetMapping(path = "/{displayInfoId}")
	public String detail(@PathVariable(name = "displayInfoId", required = true) long displayInfoId,
		ModelMap model) {
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
	@GetMapping(path = "/{displayInfoId}/review")
	public String review(@PathVariable(name = "displayInfoId", required = true) long displayInfoId,
		ModelMap model) {
		CommentPageInfo reviewPageInfo = commentService.getCommentPageInfoByDisplayInfoId(displayInfoId);
		model.addAttribute("pageInfo", reviewPageInfo);
		model.addAttribute("displayInfoId", displayInfoId);

		return "review";
	}

	/**
	 * 예약페이지에 표시할 정보 담아서 url맵핑한다
	 * @param displayInfoId 조회할 상품
	 * @param model 표시할 정보를 담는다
	 * @return 뷰이름 리턴
	 */
	@GetMapping(path = "/{displayInfoId}/reservation")
	public String getReservation(@PathVariable(name = "displayInfoId", required = true) long displayInfoId,
		ModelMap model) {
		ReservationPageInfo reservationPageInfo = reservationService
			.getReservationPageInfoByDisplayInfoId(displayInfoId);
		model.addAttribute("pageInfo", reservationPageInfo);
		return "reservation";
	}
	
	@GetMapping(path = "/{productId}/comment")
	public String getComment(@PathVariable(name = "productId", required = true) long productId, ModelMap model) {
		model.addAttribute("productId", productId);
		model.addAttribute("description", productService.getProductDescriptionByProductId(productId));
		return "reviewWrite";
	}
}
