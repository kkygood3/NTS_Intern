package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nts.reservation.service.CommentService;
import com.nts.reservation.service.ProductService;
import com.nts.reservation.service.ReservationService;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ReservationService reservationService;
	
	@GetMapping
	public String getReservation() {
		return "myreservation";
	}
	
	/**
	 * 리뷰작성 페이지에 필요한 정보 담아서 url 맵핑한다
	 * @param productId 리뷰쓸 상품
	 * @param model 표시할 정보
	 * @return 뷰이름 리턴
	 */
	@GetMapping("/{reservationInfoId}/comment")
	public String getComment(ModelMap model, 
			@PathVariable(name = "reservationInfoId", required = true) long reservationInfoId) {
		model.addAttribute("productInfo", productService.getProductInfoByReservationInfoId(reservationInfoId));
		return "reviewWrite";
	}
	
	/**
	 * 리뷰 등록한다
	 * @return
	 */
	@PostMapping("/{productId}/comment")
	public String name() {
		
		return "redirect:detail/{displayInfoId}/review";
	}
}
