package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.dto.ReservationUserComment;
import com.nts.reservation.service.CommentService;
import com.nts.reservation.service.ProductService;

/**
 * 예약상품관련 페이지 url 맵핑
 * 
 * @author si yoon
 *
 */
@Controller
@RequestMapping("/reservation")
public class ReservationController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CommentService commentService;

	/**
	 * 예약 확인페이지 url 맵핑
	 * 
	 * @return 뷰이름
	 */
	@GetMapping
	public String getReservation() {
		return "myreservation";
	}

	/**
	 * 리뷰작성 페이지에 필요한 정보 담아서 url 맵핑한다
	 * 
	 * @param productId 리뷰쓸 상품
	 * @param model     표시할 정보
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
	 * 
	 * @return 등록한 리뷰 보기
	 */
	@PostMapping("/{reservationInfoId}/comment")
	public String postComment(@PathVariable(name = "reservationInfoId", required = true) long reservationInfoId,
			@RequestParam(name = "product_id", required = true) long productId, @RequestParam("comment") String comment,
			@RequestParam("score") double score, @RequestParam("file") MultipartFile image) {
		commentService.addReservationUserComment(
				new ReservationUserComment(productId, reservationInfoId, comment, score), image);
		return "redirect:/product/" + productId + "/comment";
	}
}
